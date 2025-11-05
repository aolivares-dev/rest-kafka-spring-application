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

            var topic = getTopic();

            if (topic == null || topic.isBlank()) {
                log.error("No topic configurado para publicar (getTopic() returned null or empty). Mensaje: {}", message);
                // intentar ruta de fallo
                onFail(message);
                return;
            }

            var payload = objectMapper.writeValueAsString(message);

            log.info("Publicando mensaje en [{}]: {}", topic, payload);

            kafkaTemplate.send(topic, payload);
        } catch (Exception e) {
            log.error("Error serializando o enviando el mensaje: {}", e.getMessage(), e);

            onFail(message);
        }
    }

    public <T> void onFail(T payload) {
        String topicError = getTopicError();
        if (topicError == null || topicError.isBlank()) {
            log.error("No error topic configured (getTopicError() returned null or empty). Mensaje no enviado: {}", payload);
            return;
        }
        kafkaTemplate.send(topicError, payload);
    }

    public abstract String getTopic();

    public abstract String getTopicError();

    protected abstract void setDefaultBodyValues(Object message) throws JsonProcessingException;
}