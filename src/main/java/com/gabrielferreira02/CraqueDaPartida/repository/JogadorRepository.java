package com.gabrielferreira02.CraqueDaPartida.repository;

import com.gabrielferreira02.CraqueDaPartida.entity.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Long> {
}
