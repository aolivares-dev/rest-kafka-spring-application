package com.yappy.trnxd.backend.transaction.junior.cores.personal_payment.adapter.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yappy.trnxd.backend.transaction.junior.library.adapter.producer.ProducerTemplate;
import com.yappy.trnxd.backend.transaction.junior.library.config.KafkaTopicConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("P2PAuthorizeDebitProducer")
public class P2PAuthorizeDebitProducer extends ProducerTemplate {

    @Autowired
    private KafkaTopicConfiguration kafkaTopicConfiguration;

    @Override
    public String getTopic() {
        return kafkaTopicConfiguration.getP2pAuthorizeDebit();
    }

    @Override
    public String getTopicError() {
        return kafkaTopicConfiguration.getP2pError();
    }

    @Override
    protected void setDefaultBodyValues(Object message) throws JsonProcessingException {

    }
}