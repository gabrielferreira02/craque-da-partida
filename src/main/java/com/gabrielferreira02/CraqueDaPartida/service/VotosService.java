package com.gabrielferreira02.CraqueDaPartida.service;

import com.gabrielferreira02.CraqueDaPartida.config.RabbitMQConfig;
import com.gabrielferreira02.CraqueDaPartida.dto.VotoRequest;
import com.gabrielferreira02.CraqueDaPartida.entity.Enquete;
import com.gabrielferreira02.CraqueDaPartida.exception.EnqueteInativaException;
import com.gabrielferreira02.CraqueDaPartida.exception.EnqueteNotFoundException;
import com.gabrielferreira02.CraqueDaPartida.exception.JogadorNaoExisteNaEnqueteException;
import com.gabrielferreira02.CraqueDaPartida.repository.EnqueteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class VotosService {
    @Autowired
    private EnqueteRepository enqueteRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void votar(VotoRequest body) {
        Long enqueteId = body.enqueteId();
        Long jogadorId = body.jogadorId();

        if(jogadorId == null) {
            log.error("Campo jogadorId vazio");
            throw new IllegalArgumentException("Precisa votar em um jogador");
        }

        if(enqueteId == null) {
            log.error("Campo enqueteId vazio");
            throw new IllegalArgumentException("Enquete não pode ser vazia");
        }

        if(!enqueteRepository.existsById(enqueteId)) {
            log.error("Enquete {} não encontrada", enqueteId);
            throw new EnqueteNotFoundException("Enquete não encontrada");
        }

        if(!enqueteRepository.existsByJogadorId(jogadorId)) {
            log.error("Jogador {} não encontrado", jogadorId);
            throw new JogadorNaoExisteNaEnqueteException("Jogador não encontrado na enquete");
        }

        Optional<Enquete> enquete = enqueteRepository.findById(enqueteId);

        if(!enquete.get().isAtiva()) {
            log.error("Enquete {} está inativa", enqueteId);
            throw new EnqueteInativaException("Enquete encerrada");
        }

       rabbitTemplate.convertAndSend(
               RabbitMQConfig.EXCHANGE_NOME,
               RabbitMQConfig.ROUTING_KEY,
               body
       );

        log.info("Voto enviado para a fila");
    }

}
