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

    private String p2pBeginTopic;
    private String p2pBeginErrorTopic;
    private String p2pBeginGroupTopic;

    private String p2pAuthorizeDebitTopic;
    private String p2pAuthorizeCreditTopic;

    private String p2pCompleteDebitTopic;
    private String p2pCompleteCreditTopic;
    private String enyTopic;
}
