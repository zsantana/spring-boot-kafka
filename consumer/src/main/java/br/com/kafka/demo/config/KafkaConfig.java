package br.com.kafka.demo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

import br.com.kafka.demo.exception.KafkaErrorHandler;

@Configuration
public class KafkaConfig {

    
    @Value("${kafka.consumerKafka.topics}")
    private String topicName;

    private final KafkaErrorHandler kafkaErrorHandler;
    

    public KafkaConfig(KafkaErrorHandler kafkaErrorHandler) {
        this.kafkaErrorHandler = kafkaErrorHandler;
    }


    @Bean
    public NewTopic creaTopic(){
        return new NewTopic(topicName, 3, (short)1);
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

