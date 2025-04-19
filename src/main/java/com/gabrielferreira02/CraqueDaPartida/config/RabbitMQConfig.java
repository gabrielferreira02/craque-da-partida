package com.gabrielferreira02.CraqueDaPartida.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NOME = "amq.direct";
    public static final String QUEUE_NOME = "craque.partida.queue";
    public static final String ROUTING_KEY = "voto.registrado";

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NOME);
    }

    @Bean
    public Queue queue() {
        return QueueBuilder.durable(QUEUE_NOME).build();
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ROUTING_KEY);
    }
}
