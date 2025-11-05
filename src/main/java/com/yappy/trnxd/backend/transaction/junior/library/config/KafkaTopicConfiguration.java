package com.yappy.trnxd.backend.transaction.junior.library.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("kafka.topic")
public class KafkaTopicConfiguration {

    private String p2pBegin;
    private String p2pBeginError;
    private String p2pBeginGroup;

    private String p2pAuthorizeDebit;
    private String p2pAuthorizeCredit;

    private String p2pCompleteDebit;
    private String p2pCompleteCredit;

    private String p2pError;
}
