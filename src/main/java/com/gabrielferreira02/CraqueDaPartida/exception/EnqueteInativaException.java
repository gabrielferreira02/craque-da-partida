package com.gabrielferreira02.CraqueDaPartida.exception;

public class EnqueteInativaException extends RuntimeException{
    public EnqueteInativaException() {
    }

    public EnqueteInativaException(String message) {
        super(message);
    }
}
