package com.yappy.trnxd.backend.transaction.junior.cores.personal_payment.adapter.producer;


import com.yappy.trnxd.backend.transaction.junior.library.config.KafkaTopicConfiguration;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BeginInterbankP2PTransactionProducer {

//    private final KafkaTemplate<String, TrainingCommand> kafkaTemplate;
//
//    @Value("${kafka.topic.commands}")
//    private String topic;
//
//    public BeginInterbankP2PTransactionProducer(KafkaTemplate<String, TrainingCommand> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    public void sendCommand(TrainingCommand command) {
//        kafkaTemplate.send(topic, command);
//    }
}