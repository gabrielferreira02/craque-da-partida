package com.gabrielferreira02.CraqueDaPartida.repository;

import com.gabrielferreira02.CraqueDaPartida.entity.Enquete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EnqueteRepository extends JpaRepository<Enquete, Long> {

    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END " +
            "FROM Enquete e " +
            "WHERE e.jogador1.id = :id OR e.jogador2.id = :id OR e.jogador3.id = :id")
    boolean existsByJogadorId(@Param("id") Long jogadorId);

}
