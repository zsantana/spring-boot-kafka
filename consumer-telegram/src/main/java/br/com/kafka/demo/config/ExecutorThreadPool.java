package br.com.kafka.demo.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ExecutorThreadPool {

    public class AsyncConfig {

        // @Bean(name = "customTaskExecutor")
        // public Executor taskExecutor() {

        //     ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //     executor.setCorePoolSize(4);           // 2 x número de processadores disponíveis.
        //     executor.setMaxPoolSize(30);            // Maior, mas limitado, para picos de carga.
        //     executor.setQueueCapacity(50);        // Menor fila para evitar acúmulo excessivo de tarefas.
        //     executor.setThreadNamePrefix("AsyncExecutor-");
        //     executor.setKeepAliveSeconds(60);  // *Threads* acima do Core serão finalizadas após inatividade.
        //     executor.initialize();

        //     return executor;
        // }
    }
    
}
