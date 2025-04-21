package com.gabrielferreira02.CraqueDaPartida.service;

import com.gabrielferreira02.CraqueDaPartida.dto.EnqueteRequest;
import com.gabrielferreira02.CraqueDaPartida.dto.JogadorVoto;
import com.gabrielferreira02.CraqueDaPartida.dto.VotoResponse;
import com.gabrielferreira02.CraqueDaPartida.entity.Enquete;
import com.gabrielferreira02.CraqueDaPartida.entity.Jogador;
import com.gabrielferreira02.CraqueDaPartida.exception.EnqueteNotFoundException;
import com.gabrielferreira02.CraqueDaPartida.repository.EnqueteRepository;
import com.gabrielferreira02.CraqueDaPartida.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Optional;

@Service
public class EnqueteService {

    @Autowired
    private EnqueteRepository enqueteRepository;
    @Autowired
    private JogadorRepository jogadorRepository;

    public void criar(EnqueteRequest request) {
        Long jogador1 = request.jogador1Id(), jogador2 = request.jogador2Id(), jogador3 = request.jogador3Id();
        if(jogador1 == null ||
                jogador2 == null ||
                jogador3 == null) {
            throw new IllegalArgumentException("Precisam ter 3 jogadores listados");
        }

        if(jogador1.equals(jogador2)) {
            throw new IllegalArgumentException("Não é permitido o mesmo jogador na enquete");
        }

        if(jogador1.equals(jogador3)) {
            throw new IllegalArgumentException("Não é permitido o mesmo jogador na enquete");
        }

        if(jogador2.equals(jogador3)) {
            throw new IllegalArgumentException("Não é permitido o mesmo jogador na enquete");
        }

        Optional<Jogador> jogador1Entity = jogadorRepository.findById(jogador1);
        Optional<Jogador> jogador2Entity = jogadorRepository.findById(jogador2);
        Optional<Jogador> jogador3Entity = jogadorRepository.findById(jogador3);

        if(jogador1Entity.isEmpty()) {
            throw new IllegalArgumentException("Jogador não encontrado");
        }

        if(jogador2Entity.isEmpty()) {
            throw new IllegalArgumentException("Jogador não encontrado");
        }

        if(jogador3Entity.isEmpty()) {
            throw new IllegalArgumentException("Jogador não encontrado");
        }

        Enquete enquete = new Enquete();
        enquete.setJogador1(jogador1Entity.get());
        enquete.setJogador2(jogador2Entity.get());
        enquete.setJogador3(jogador3Entity.get());
        enquete.setAtiva(true);

        enqueteRepository.save(enquete);
    }

    public void desativar(Long id) {
        Optional<Enquete> enquete = enqueteRepository.findById(id);

        if(enquete.isEmpty()) {
            throw new EnqueteNotFoundException("Enquete não existe");
        }

        enquete.get().setAtiva(false);
        enqueteRepository.save(enquete.get());
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
