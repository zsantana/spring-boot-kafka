package br.com.kafka.demo.kafka;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
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

    // groupId = ORDER_STATUS_GROUP_ID_PREFIX + "#{ T(java.util.UUID).randomUUID().toString() }"
    // @KafkaListener(topics = "cashhub-3", groupId = "group-cahshub-3", concurrency = "6")
    @KafkaListener(topics = "${kafka.consumerKafka.topics}", 
                   groupId = "${kafka.consumerKafka.groupId}",
                   concurrency = "3")
    public void listen(@Valid PayloadDTO payloadDTO) {

       try {
        
        log.info("### Received message: {}", payloadDTO);
        service.create(payloadDTO);
        
       } catch (Exception e) {
            serviceDLT.enviarDLT(e, payloadDTO);
       }
       
        
    }

}
