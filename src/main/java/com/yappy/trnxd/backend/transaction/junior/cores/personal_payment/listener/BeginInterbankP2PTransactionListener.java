package com.yappy.trnxd.backend.transaction.junior.cores.personal_payment.listener;

import com.yappy.trnxd.backend.transaction.junior.cores.personal_payment.adapter.consumer.BeginInterbankP2PTransactionConsumer;
import com.yappy.trnxd.backend.transaction.junior.cross_model.model.BeginP2PTransactionDTO;
import com.yappy.trnxd.backend.transaction.junior.library.adapter.consumer.ConsumerTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component("BeginInterbankP2PTransactionListener")
public class BeginInterbankP2PTransactionListener {

    @Autowired
    @Qualifier("BeginInterbankP2PTransactionConsumer")
    private ConsumerTemplate<BeginP2PTransactionDTO> consumer;

    @KafkaListener(id = "BeginInterbankP2PTransactionListener",
            topics = "${kafka.topic.inter-P2P-begin}",
            groupId = "${kafka.topic.inter-P2P-begin-group}")
    public void listen(ConsumerRecord<String, String> consumerRecord) {
        consumer.execute(consumerRecord);
    }
}
