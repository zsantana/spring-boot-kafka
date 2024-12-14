package br.com.kafka.demo.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Service;

import br.com.kafka.demo.dto.ErroMessageDLT;
import br.com.kafka.demo.exception.KafkaErrorHandler;
import br.com.kafka.demo.kafka.ProducerKafka;


@Service
public class NotificacaoDeadLetterService {

    private static Logger log = LoggerFactory.getLogger(KafkaErrorHandler.class);

    private final ProducerKafka producerKafka;
    private final String topicDLT;

    public NotificacaoDeadLetterService(ProducerKafka producerKafka,
                             @Value("${kafka.consumerKafka.DLT}") String topicDLT) {

        this.producerKafka = producerKafka;
        this.topicDLT = topicDLT;
    }
    

    public void handleRemaining(Exception thrownException, List<ConsumerRecord<?, ?>> records, Consumer<?, ?> consumer,
        MessageListenerContainer container) {

        // Check if records is not null and not empty
        if (records != null && !records.isEmpty()) {

            ConsumerRecord<?, ?> record = records.get(0);

            // Extrai informações do erro
            var rootCause = ExceptionUtils.getRootCauseMessage(thrownException);
            var sstackTrace = Optional.ofNullable(thrownException.getCause()).map(Throwable::toString).orElse("");
            var message = Optional.ofNullable(thrownException.getMessage()).orElse("");
            var payload = record.value() != null ? record.value().toString() : "N/A"; 
            // Cria mensagem de erro para a DLT
            ErroMessageDLT erroMessage = new ErroMessageDLT(
                    rootCause,
                    message,
                    sstackTrace,
                    payload
            );

            log.error("### KafkaErrorHandler rootCause: {} message: {}", rootCause, message);

            // Envia mensagem para a DLT
            if (producerKafka != null && topicDLT != null) {
                producerKafka.execute(topicDLT, erroMessage);
            } else {
                log.error("### producerKafka or topicDLT is null");
            }
        } else {
            log.error("### records is null or empty");
        }
    }

    public void enviarDLT(){
        
    }

  
}
