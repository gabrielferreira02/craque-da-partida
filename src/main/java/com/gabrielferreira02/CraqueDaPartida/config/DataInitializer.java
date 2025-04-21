package com.gabrielferreira02.CraqueDaPartida.config;

import com.gabrielferreira02.CraqueDaPartida.entity.Enquete;
import com.gabrielferreira02.CraqueDaPartida.entity.Jogador;
import com.gabrielferreira02.CraqueDaPartida.repository.EnqueteRepository;
import com.gabrielferreira02.CraqueDaPartida.repository.JogadorRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    @Transactional
    CommandLineRunner init(
            EnqueteRepository enqueteRepository,
            JogadorRepository jogadorRepository
    ) {
        return args -> {
            Jogador j1 = jogadorRepository.save(
                    new Jogador(null, "Pablo Vegetti", "Vasco da Gama")
            );

            Jogador j2 = jogadorRepository.save(
                    new Jogador(null, "Philipe Coutinho", "Vasco da Gama")
            );

            Jogador j3 = jogadorRepository.save(
                    new Jogador(null, "Leo Jardim", "Vasco da Gama")
            );

            Enquete enquete = new Enquete();
            enquete.setJogador1(j1);
            enquete.setJogador2(j2);
            enquete.setJogador3(j3);
            enquete.setAtiva(true);

            enqueteRepository.save(enquete);
        };
    }
}
