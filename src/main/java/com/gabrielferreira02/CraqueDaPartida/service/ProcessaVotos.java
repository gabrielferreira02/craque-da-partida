package com.gabrielferreira02.CraqueDaPartida.service;

import com.gabrielferreira02.CraqueDaPartida.config.RabbitMQConfig;
import com.gabrielferreira02.CraqueDaPartida.dto.VotoRequest;
import com.gabrielferreira02.CraqueDaPartida.entity.Enquete;
import com.gabrielferreira02.CraqueDaPartida.repository.EnqueteRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProcessaVotos {

    @Autowired
    private EnqueteRepository enqueteRepository;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NOME)
    public void processarVoto(VotoRequest voto) {
        Optional<Enquete> getEnquete = enqueteRepository.findById(voto.enqueteId());

        Enquete enquete = getEnquete.get();
        enquete.adicionarVoto(voto.jogadorId());
        enqueteRepository.save(enquete);
    }
}
