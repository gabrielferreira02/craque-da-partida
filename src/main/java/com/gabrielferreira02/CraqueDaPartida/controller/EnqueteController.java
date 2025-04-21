package com.gabrielferreira02.CraqueDaPartida.controller;

import com.gabrielferreira02.CraqueDaPartida.dto.EnqueteRequest;
import com.gabrielferreira02.CraqueDaPartida.service.EnqueteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enquete")
public class EnqueteController {

    @Autowired
    private EnqueteService enqueteService;

    @PostMapping
    public ResponseEntity<Object> criar(@RequestBody EnqueteRequest request) {
        enqueteService.criar(request);
        return ResponseEntity.ok("Enquete criada");
    }

    @PutMapping("desativar/{id}")
    public ResponseEntity<Void> desativar(@PathVariable("id") Long id) {
        enqueteService.desativar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
