package br.com.kafka.demo.kafka;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import br.com.kafka.demo.dto.PayloadDTO;
import br.com.kafka.demo.service.TituloService;
import br.com.kafka.demo.websocket.WebSocketHandler;
import jakarta.validation.Valid;

@Component
@Validated
public class ConsumerKafka {
    
    private static Logger log = LoggerFactory.getLogger(ConsumerKafka.class);
   
    private final WebSocketHandler webSocketHandler;

    public ConsumerKafka(WebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }

    // groupId = ORDER_STATUS_GROUP_ID_PREFIX + "#{ T(java.util.UUID).randomUUID().toString() }"
    // @KafkaListener(topics = "cashhub-3", groupId = "group-cahshub-3", concurrency = "6")
    @KafkaListener(topics = "${kafka.consumerKafka.topics}", 
                   groupId = "${kafka.consumerKafka.groupId}",
                   concurrency = "3")
    public void listen(@Valid PayloadDTO payloadDTO) {

        System.out.println("Received message: " + payloadDTO);
        webSocketHandler.sendMessage(payloadDTO.toString());

        
    }

}
