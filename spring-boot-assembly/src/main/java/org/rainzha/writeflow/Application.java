package org.rainzha.writeflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        proxyBeanMethods = false,
        scanBasePackages = {"org.rainzha.writeflow", "org.rainzha.transactional"})
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}