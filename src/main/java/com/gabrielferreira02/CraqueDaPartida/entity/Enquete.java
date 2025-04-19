package com.gabrielferreira02.CraqueDaPartida.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Enquete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Jogador jogador1;
    private Long votosJogador1 = 0L;

    @ManyToOne
    private Jogador jogador2;
    private Long votosJogador2 = 0L;

    @ManyToOne
    private Jogador jogador3;
    private Long votosJogador3 = 0L;

    private boolean ativa;

    public void adicionarVoto(Long jogadorId) {
        if(jogador1.getId().equals(jogadorId)) {
            votosJogador1++;
        } else if(jogador2.getId().equals(jogadorId)) {
            votosJogador2++;
        } else {
            votosJogador3++;
        }
    }
}
