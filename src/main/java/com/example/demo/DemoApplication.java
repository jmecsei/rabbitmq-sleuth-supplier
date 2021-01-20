package com.example.demo;

import com.example.demo.async.AsyncSupplier;
import com.example.demo.sync.SyncProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@SpringBootApplication
public class DemoApplication {

    @Autowired
    private SyncProducer messageProducer;

    @Autowired
    private AsyncSupplier ipnMessageSupplier;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @PostMapping("async")
    public void asyncMessage(@RequestBody String message) {
        log.info("Got async message: {}", message);
        ipnMessageSupplier.sendMessage(message);
    }

    @PostMapping("sync")
    public void syncMessage(@RequestBody String message) {
        log.info("Got sync message: {}", message);
        messageProducer.sendMessage(message);
    }
}
