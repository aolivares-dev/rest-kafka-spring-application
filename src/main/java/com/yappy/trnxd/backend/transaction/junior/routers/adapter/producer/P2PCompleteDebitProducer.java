package com.yappy.trnxd.backend.transaction.junior.routers.adapter.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yappy.trnxd.backend.transaction.junior.library.adapter.producer.ProducerTemplate;
import com.yappy.trnxd.backend.transaction.junior.library.config.KafkaTopicConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class P2PCompleteDebitProducer extends ProducerTemplate {

    @Autowired
    private KafkaTopicConfiguration kafkaTopicConfiguration;

    @Override
    public String getTopic() {
        return kafkaTopicConfiguration.getP2pCompleteDebitTopic();
    }

    @Override
    public String getErrorTopic() {
        return "";
    }

    @Override
    protected void setDefaultBodyValues(Object message) throws JsonProcessingException {

    }
}