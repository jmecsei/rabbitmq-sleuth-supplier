package com.example.demo.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Supplier;

@Slf4j
@Component
public class AsyncSupplier implements Supplier<String> {
    private final LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue();

    public void sendMessage(String message) {
        linkedBlockingQueue.offer(message);
    }

    @Override
    public String get() {
        String message = linkedBlockingQueue.poll();
        if (message != null) {
            log.info("Send async message: {}", message);
        }
        return message;
    }
}
