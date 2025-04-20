package com.gabrielferreira02.CraqueDaPartida.dto;

import java.io.Serializable;

public record VotoRequest(Long jogadorId, Long enqueteId) implements Serializable {
}
