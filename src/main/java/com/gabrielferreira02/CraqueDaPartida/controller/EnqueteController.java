package com.gabrielferreira02.CraqueDaPartida.controller;

import com.gabrielferreira02.CraqueDaPartida.dto.EnqueteRequest;
import com.gabrielferreira02.CraqueDaPartida.dto.ErroResponse;
import com.gabrielferreira02.CraqueDaPartida.dto.VotoResponse;
import com.gabrielferreira02.CraqueDaPartida.service.EnqueteService;
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
@RequestMapping("/enquete")
@Tag(name = "enquete", description = "Realiza operações nas enquetes")
public class EnqueteController {

    @Autowired
    private EnqueteService enqueteService;

    @GetMapping("{id}")
    @Operation(
            summary = "Recupera enquete",
            description = "Recupera o resultado de uma enquete por seu id",
            tags = "enquete",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Enquete encontrada",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = VotoResponse.class
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Enquete não encontrada",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = ErroResponse.class
                                    )
                            )
                    )
            }
    )
    public ResponseEntity<VotoResponse> resultado(@PathVariable("id") Long id) {
        return ResponseEntity.ok(enqueteService.resultado(id));
    }

    @PostMapping
    @Operation(
            summary = "Criar enquete",
            description = "Cria uma nova enquete ativa",
            tags = "enquete",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Enquete criada"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Falha ao criar enquete",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = ErroResponse.class
                                    )
                            )
                    )
            }
    )
    public ResponseEntity<Object> criar(@RequestBody EnqueteRequest request) {
        enqueteService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("desativar/{id}")
    @Operation(
            summary = "Desativar enquete",
            description = "Desativa uma enquete impossibilitando registro de novos votos",
            tags = "enquete",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Enquete desativada"
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
    public ResponseEntity<Void> desativar(@PathVariable("id") Long id) {
        enqueteService.desativar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
