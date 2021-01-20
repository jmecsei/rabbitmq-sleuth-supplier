package com.example.demo.sync;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

interface MessageBinding {
    @Output("syncSupplier")
    MessageChannel messageChannel();
}
