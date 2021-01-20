package com.example.demo.sync;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

@Slf4j
@EnableBinding(MessageBinding.class)
public class SyncProducer {
    private final MessageChannel messageChannel;

    public SyncProducer(MessageBinding messageBinding) {
        messageChannel = messageBinding.messageChannel();
    }

    public void sendMessage(String message) {
        log.info("Send sync message: {}", message);
        messageChannel.send(MessageBuilder.withPayload(message).build());
    }
}
