package com.gabrielferreira02.CraqueDaPartida.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "API craque da partida",
                description = "Api que simula de forma simples o esquema de votação de melhor jogador de uma partida, semelhante a usada em várias emissoras em transmissões esportivas",
                version = "1.0.0",
                contact = @Contact(
                        name = "Gabriel Ferreira",
                        url = "https://github.com/gabrielferreira02",
                        email = "gabrielf.04.2002@gmail.com"
                )
        ),
        servers = {
                @Server(
                        description = "DEV SERVER",
                        url = "http://localhost:8080"
                )
        }
)
public class SwaggerConfig {
}
