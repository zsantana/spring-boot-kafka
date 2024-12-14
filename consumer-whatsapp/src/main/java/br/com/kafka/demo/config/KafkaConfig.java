package br.com.kafka.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

import br.com.kafka.demo.exception.KafkaErrorHandler;

@Configuration
public class KafkaConfig {

    private final KafkaErrorHandler kafkaErrorHandler;

    public KafkaConfig(KafkaErrorHandler kafkaErrorHandler) {
        this.kafkaErrorHandler = kafkaErrorHandler;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory(
            ConsumerFactory<String, Object> consumerFactory) {

        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);

        // Configura o error handler
        factory.setCommonErrorHandler(kafkaErrorHandler);

        return factory;
    }
    




}

