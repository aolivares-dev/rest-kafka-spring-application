package com.yappy.trnxd.backend.transaction.junior.library.adapter.consumer;

import com.fasterxml.jackson.core.type.TypeReference;
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
    private KafkaTemplate<String, Object> kafkaTemplate;

    protected abstract String getErrorTopicName();

    protected abstract CommandTemplate<TransactionRequestEntity<REQUEST>, TransactionResponseEntity<REQUEST>> getCommand();

    public void execute(ConsumerRecord<String, String> record) {
        try {
            Class<TransactionRequestEntity> clazz = TransactionRequestEntity.class;
            TransactionRequestEntity<REQUEST> transactionRequestEntity =
                    objectMapper.readValue(record.value(), clazz);

            getCommand().execute(transactionRequestEntity, transactionRequestEntity.getProfile());
        } catch (Exception e) {
            log.error("Error al procesar el mensaje: {}", e.getMessage(), e);
            kafkaTemplate.send(getErrorTopicName(), record.value());
        }
    }
}