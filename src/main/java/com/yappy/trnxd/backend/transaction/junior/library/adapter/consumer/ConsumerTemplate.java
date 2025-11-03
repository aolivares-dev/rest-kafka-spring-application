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

    protected abstract String getErrorTopicName();

    protected abstract Class<REQUEST> getRequestType();

    protected abstract CommandTemplate<TransactionRequestEntity<REQUEST>, TransactionResponseEntity<REQUEST>> getCommand();

    public void execute(ConsumerRecord<String, String> record) {
        try {
            var javaType = objectMapper.getTypeFactory().constructParametricType(TransactionRequestEntity.class, getRequestType());

            TransactionRequestEntity<REQUEST> transactionRequestEntity = objectMapper.readValue(record.value(), javaType);

            log.info("Procesando mensaje desde topic={} key={} payload={}", record.topic(), record.key(), record.value());
            setDefaultValues(transactionRequestEntity.getBody());

            getCommand().execute(transactionRequestEntity, transactionRequestEntity.getProfile());

        } catch (Exception e) {
            log.error("Error procesando mensaje del topic {} con key {}: {}", record.topic(), record.key(), e.getMessage(), e);
            kafkaTemplate.send(getErrorTopicName(), record.value());
        }
    }

    protected abstract void setDefaultValues(REQUEST message);
}