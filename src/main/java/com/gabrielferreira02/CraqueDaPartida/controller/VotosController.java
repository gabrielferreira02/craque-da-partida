package com.gabrielferreira02.CraqueDaPartida.controller;

import com.gabrielferreira02.CraqueDaPartida.dto.VotoRequest;
import com.gabrielferreira02.CraqueDaPartida.dto.VotoResponse;
import com.gabrielferreira02.CraqueDaPartida.service.VotosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/votos")
public class VotosController {

    @Autowired
    private VotosService votosService;

    @PostMapping
    public ResponseEntity<Void> votar(@RequestBody VotoRequest body) {
        votosService.votar(body);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<VotoResponse> resultado(@PathVariable("id") Long id) {
        return ResponseEntity.ok(votosService.resultado(id));
    }

}
