package io.goorm.etdservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EtdServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EtdServiceApplication.class, args);
    }

}
