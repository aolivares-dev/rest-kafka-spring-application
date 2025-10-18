package com.yappy.trnxd.backend.transaction.junior.library.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("kafka.topic")
public class KafkaTopicConfiguration {

    private String interP2PBegin;
    private String interP2PBeginError;
    private String interP2PBeginGroup;
}
