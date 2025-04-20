package com.gabrielferreira02.CraqueDaPartida.exception;

public class EnqueteNotFoundException extends RuntimeException {
    public EnqueteNotFoundException() {
    }

    public EnqueteNotFoundException(String message) {
        super(message);
    }
}
