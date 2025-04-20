package com.gabrielferreira02.CraqueDaPartida.exception;

import com.gabrielferreira02.CraqueDaPartida.dto.ErroResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(new ErroResponse(e.getMessage()));
    }

    @ExceptionHandler(EnqueteNotFoundException.class)
    public ResponseEntity<Void> handleEnqueteNotFoundException(EnqueteNotFoundException e) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(EnqueteInativaException.class)
    public ResponseEntity<ErroResponse> handleEnqueteInativaException(EnqueteInativaException e) {
        return ResponseEntity.badRequest().body(new ErroResponse(e.getMessage()));
    }

}
