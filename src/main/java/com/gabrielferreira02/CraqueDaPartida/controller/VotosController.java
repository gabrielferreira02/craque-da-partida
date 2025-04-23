package com.gabrielferreira02.CraqueDaPartida.controller;

import com.gabrielferreira02.CraqueDaPartida.dto.ErroResponse;
import com.gabrielferreira02.CraqueDaPartida.dto.VotoRequest;
import com.gabrielferreira02.CraqueDaPartida.dto.VotoResponse;
import com.gabrielferreira02.CraqueDaPartida.service.VotosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/votos")
@Tag(name = "voto", description = "Realização de votos")
public class VotosController {

    @Autowired
    private VotosService votosService;

    @PostMapping
    @Operation(
            summary = "Registrar voto",
            description = "Registra um novo voto para determinada enquete",
            tags = "voto",
            responses = {
                    @ApiResponse(
                        responseCode = "200",
                        description = "Voto realizado com sucesso"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Enquete não encontrada",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = ErroResponse.class
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Falha ao processar voto",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = ErroResponse.class
                                    )
                            )
                    )
            }
    )
    public ResponseEntity<Void> votar(@RequestBody VotoRequest body) {
        votosService.votar(body);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
