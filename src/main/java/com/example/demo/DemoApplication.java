package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Supplier;

@Slf4j
@RestController
@SpringBootApplication
public class DemoApplication {

    private final LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue();

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @PostMapping
    public void message(@RequestBody String message) {
        log.info("Got message: {}", message);
        linkedBlockingQueue.offer(message);
    }

    @Bean
    Supplier<String> ipnMessageSupplier() {
        return () -> {
            String message = linkedBlockingQueue.poll();
            if (message != null) {
                log.info("Send message: {}", message);
            }

            return message;
        };
    }
}
