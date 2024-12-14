package br.com.kafka.demo.service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.kafka.demo.dto.PayloadDTO;
import br.com.kafka.demo.kafka.ProducerKafka;

@Service
public class NotificacaoService {

    private static Logger log = LoggerFactory.getLogger(NotificacaoService.class);

    private final ProducerKafka kafkaTemplate;

    public NotificacaoService(final ProducerKafka kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    
    public void publicarNotificacoes(PayloadDTO payloadDTO) {

        Map<String, String> topics = Map.of(
                "topic-notif-email", "E-mail Notification",
                "topic-notif-webhook", "Webhook Notification",
                "topic-notif-whatsapp", "WhatsApp Notification",
                "topic-notif-sms", "SMS Notification"
        );

        topics.forEach((topic, descricao) -> {
            log.info("Send message to topic {} paylod {}", topic, payloadDTO);
            CompletableFuture.runAsync(() -> kafkaTemplate.execute(topic, payloadDTO));
        });
    }
    
}
