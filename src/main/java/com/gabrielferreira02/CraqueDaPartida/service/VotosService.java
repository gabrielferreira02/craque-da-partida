package com.gabrielferreira02.CraqueDaPartida.service;

import com.gabrielferreira02.CraqueDaPartida.config.RabbitMQConfig;
import com.gabrielferreira02.CraqueDaPartida.dto.JogadorVoto;
import com.gabrielferreira02.CraqueDaPartida.dto.VotoRequest;
import com.gabrielferreira02.CraqueDaPartida.dto.VotoResponse;
import com.gabrielferreira02.CraqueDaPartida.entity.Enquete;
import com.gabrielferreira02.CraqueDaPartida.exception.EnqueteInativaException;
import com.gabrielferreira02.CraqueDaPartida.exception.EnqueteNotFoundException;
import com.gabrielferreira02.CraqueDaPartida.exception.JogadorNaoExisteNaEnqueteException;
import com.gabrielferreira02.CraqueDaPartida.repository.EnqueteRepository;
import com.gabrielferreira02.CraqueDaPartida.repository.JogadorRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Optional;

@Service
public class VotosService {
    @Autowired
    private EnqueteRepository enqueteRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void votar(VotoRequest body) {
        Long enqueteId = body.enqueteId();
        Long jogadorId = body.jogadorId();

        if(jogadorId == null) {
            throw new IllegalArgumentException("Precisa votar em um jogador");
        }

        if(enqueteId == null) {
            throw new IllegalArgumentException("Enquete não pode ser vazia");
        }

        if(!enqueteRepository.existsById(enqueteId)) {
            throw new EnqueteNotFoundException("Enquete não encontrada");
        }

        if(!enqueteRepository.existsByJogadorId(jogadorId)) {
            throw new JogadorNaoExisteNaEnqueteException("Jogador não encontrado na enquete");
        }

        Optional<Enquete> enquete = enqueteRepository.findById(enqueteId);

        if(!enquete.get().isAtiva()) {
            throw new EnqueteInativaException("Enquete encerrada");
        }

       rabbitTemplate.convertAndSend(
               RabbitMQConfig.EXCHANGE_NOME,
               RabbitMQConfig.ROUTING_KEY,
               body
       );
    }

    public VotoResponse resultado(Long id) {
        Optional<Enquete> getEnquete = enqueteRepository.findById(id);

        if (getEnquete.isEmpty()) {
            throw new EnqueteNotFoundException("Enquete não encontrada");
        }

        Enquete enquete = getEnquete.get();
        long total = enquete.getVotosJogador1() + enquete.getVotosJogador2() + enquete.getVotosJogador3();
        System.out.println(total);
        return getVotoResponse(total, enquete);
    }

    private static VotoResponse getVotoResponse(long total, Enquete enquete) {
        DecimalFormat df = new DecimalFormat("#0.00");
        String porcentagemJogador1 = (total == 0) ? "0.0" : df.format((double) enquete.getVotosJogador1() / total);
        String porcentagemJogador2 = (total == 0) ? "0.0" : df.format((double) enquete.getVotosJogador2() / total);
        String porcentagemJogador3 = (total == 0) ? "0.0" : df.format((double) enquete.getVotosJogador3() / total);

        JogadorVoto jogador1 = new JogadorVoto(enquete.getJogador1().getNome(), porcentagemJogador1);
        JogadorVoto jogador2 = new JogadorVoto(enquete.getJogador2().getNome(), porcentagemJogador2);
        JogadorVoto jogador3 = new JogadorVoto(enquete.getJogador3().getNome(), porcentagemJogador3);

        return new VotoResponse(jogador1, jogador2, jogador3);
    }
}
