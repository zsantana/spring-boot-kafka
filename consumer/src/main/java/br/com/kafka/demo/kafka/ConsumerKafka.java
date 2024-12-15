package br.com.kafka.demo.kafka;


import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.RetryTopicHeaders;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

import br.com.kafka.demo.dto.PayloadDTO;
import br.com.kafka.demo.service.NotificacaoDeadLetterService;
import br.com.kafka.demo.service.TituloService;
import jakarta.validation.Valid;

@Component
public class ConsumerKafka {
    
    private static Logger log = LoggerFactory.getLogger(ConsumerKafka.class);
    private final NotificacaoDeadLetterService serviceDLT;
    private final TituloService service;
    
    public ConsumerKafka(final TituloService service,
                        final NotificacaoDeadLetterService serviceDLT) {
        this.service = service;
        this.serviceDLT = serviceDLT;

    }

    @RetryableTopic(attempts =  "4", 
                    backoff =@Backoff(delay = 500, multiplier = 1.5, maxDelay = 1000)
                    // exclude = {NullPointerException.class, RuntimeException.class}
                    )
    @KafkaListener(topics = "${kafka.consumerKafka.topics}", 
                   groupId = "${kafka.consumerKafka.groupId}",
                   concurrency = "3")
    public void listen(@Valid PayloadDTO payloadDTO, 
                        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, 
                        @Header(KafkaHeaders.OFFSET) long offset,
                        @Header(name = RetryTopicHeaders.DEFAULT_HEADER_ATTEMPTS, required = false) Integer attempt) {

        try {
            
            if (payloadDTO.nossoNumero().equals("1"))
                throw new RuntimeException("test");

            log.info("### Received message: {}, from: {} offSet: {} attempt {}", payloadDTO, topic, offset, attempt);
            service.create(payloadDTO);
        
        } catch (Exception e) {
            throw new RuntimeException("errrooooooorrrrrr");
        }                    
        
       
    }

    // @DltHandler
    // public void listenDLT(PayloadDTO payloadDTO, 
    //                     @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, 
    //                     @Header(KafkaHeaders.OFFSET) long offset,
    //                     @Header(RetryTopicHeaders.DEFAULT_HEADER_ATTEMPTS) Integer attempt) {
    //     log.info("### DLT Received : {} , from {} , offset {} attempt {}", payloadDTO, topic, offset, attempt);
    // }

}
