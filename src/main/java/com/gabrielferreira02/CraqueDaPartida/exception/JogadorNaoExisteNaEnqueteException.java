package com.gabrielferreira02.CraqueDaPartida.exception;

public class JogadorNaoExisteNaEnqueteException extends RuntimeException{
    public JogadorNaoExisteNaEnqueteException() {
    }

    public JogadorNaoExisteNaEnqueteException(String message) {
        super(message);
    }
}
