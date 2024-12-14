package br.com.kafka.demo.exception;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.com.kafka.demo.service.NotificacaoDeadLetterService;

import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;


@Component
public class KafkaErrorHandler extends DefaultErrorHandler {

    private static Logger log = LoggerFactory.getLogger(KafkaErrorHandler.class);
    private final NotificacaoDeadLetterService notificacaoDeadLetterService;

    public KafkaErrorHandler(NotificacaoDeadLetterService notificacaoDeadLetterService) {
        super(
            // Custom log consumer
            (record, ex) -> {
                // Suprimir logs padrão
                log.debug("### Supressing log for exception in record: {}", record, ex);
            }
        );


        this.notificacaoDeadLetterService = notificacaoDeadLetterService;
    }



    @Override
    public void handleRemaining(Exception thrownException, List<ConsumerRecord<?, ?>> records, Consumer<?, ?> consumer,
        MessageListenerContainer container) {

        super.handleRemaining(thrownException, records, consumer, container);
        CompletableFuture.runAsync(() -> notificacaoDeadLetterService.handleRemaining (thrownException, records, consumer, container));;
    
    }
    
}
