package io.goorm.etdservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableMongoRepositories
public class EtdServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EtdServiceApplication.class, args);
    }

}
