package com.yappy.trnxd.backend.transaction.junior.library.adapter.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yappy.trnxd.backend.transaction.junior.cross_model.model.TransactionRequestEntity;
import com.yappy.trnxd.backend.transaction.junior.cross_model.model.TransactionResponseEntity;
import com.yappy.trnxd.backend.transaction.junior.library.command.CommandTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
public abstract class ConsumerTemplate<REQUEST> {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    protected abstract String getTopicError();

    protected abstract Class<REQUEST> getRequestType();

    protected abstract CommandTemplate<TransactionRequestEntity<REQUEST>, TransactionResponseEntity<REQUEST>> getCommand();

    public void execute(ConsumerRecord<String, String> record) {
        try {
            var javaType = objectMapper.getTypeFactory().constructParametricType(TransactionRequestEntity.class, getRequestType());

            TransactionRequestEntity<REQUEST> transactionRequestEntity = objectMapper.readValue(record.value(), javaType);

            setDefaultValues(transactionRequestEntity.getBody());

            getCommand().execute(transactionRequestEntity, transactionRequestEntity.getProfile());

        } catch (Exception e) {
            log.error("Error en el consumo de mensaje: {}", e.getMessage(), e);
            String topicError = getTopicError();
            if (topicError == null || topicError.isBlank()) {
                log.error("No error topic configured (getTopicError() returned null or empty). Mensaje no reenviado: {}", record.value());
                return;
            }
            kafkaTemplate.send(topicError, record.value());
        }
    }

    protected void setDefaultValues(REQUEST message) {
    }
}