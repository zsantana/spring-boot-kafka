package br.com.kafka.demo.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProdutorKafka {
    
    private KafkaTemplate<String, Object> kafkaTemplate;

    public ProdutorKafka(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void execute (String topic, Object payload){
        kafkaTemplate.send(topic, payload);
    }

}
