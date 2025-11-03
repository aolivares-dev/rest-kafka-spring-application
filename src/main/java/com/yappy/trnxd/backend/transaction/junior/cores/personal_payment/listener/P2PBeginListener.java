package com.yappy.trnxd.backend.transaction.junior.cores.personal_payment.listener;

import com.yappy.trnxd.backend.transaction.junior.cross_model.model.BeginTransactionDTO;
import com.yappy.trnxd.backend.transaction.junior.library.adapter.consumer.ConsumerTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component("P2PBeginListener")
public class P2PBeginListener {

    @Autowired
    @Qualifier("P2PBeginConsumer")
    private ConsumerTemplate<BeginTransactionDTO> consumer;

    @KafkaListener(id = "P2PBeginListener",
            topics = "${kafka.topic.p2p.begin}",
            groupId = "${kafka.topic.p2p.begin-group}")
    public void listen(ConsumerRecord<String, String> consumerRecord) {
        consumer.execute(consumerRecord);
    }
}
