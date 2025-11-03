package com.yappy.trnxd.backend.transaction.junior.library.adapter.producer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yappy.trnxd.backend.transaction.junior.library.config.KafkaTopicConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public abstract class ProducerTemplate {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private KafkaTopicConfiguration kafkaTopicConfiguration;

    @Autowired
    private ObjectMapper objectMapper;

    public <T> void onSuccess(T message) throws JsonProcessingException {
        try {

            setDefaultBodyValues(message);

            var payload = objectMapper.writeValueAsString(message);

            log.info("Publicando mensaje en [{}]: {}", getTopic(), payload);

            kafkaTemplate.send(getTopic(), payload);
        } catch (Exception e) {
            log.error("Error serializando o enviando el mensaje: {}", e.getMessage(), e);

            onFail(message);
        }
    }

    public <T> void onFail(T payload) {
        kafkaTemplate.send(getErrorTopic(), payload);
    }

    public abstract String getTopic();

    public abstract String getErrorTopic();

    protected abstract void setDefaultBodyValues(Object message) throws JsonProcessingException;
}