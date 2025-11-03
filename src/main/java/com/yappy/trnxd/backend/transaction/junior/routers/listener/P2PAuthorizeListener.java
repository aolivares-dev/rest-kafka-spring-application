package com.yappy.trnxd.backend.transaction.junior.routers.listener;

import com.yappy.trnxd.backend.transaction.junior.cross_model.model.BeginTransactionDTO;
import com.yappy.trnxd.backend.transaction.junior.library.adapter.consumer.ConsumerTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component("P2PAuthorizeListener")
public class P2PAuthorizeListener {

    @Autowired
    @Qualifier("P2PAuthorizeDebitConsumer")
    private ConsumerTemplate<BeginTransactionDTO> debitConsumer;

    @Autowired
    @Qualifier("P2PAuthorizeCreditConsumer")
    private ConsumerTemplate<BeginTransactionDTO> creditConsumer;

    @KafkaListener(id = "P2PAuthorizeDebitListener", topics = "${kafka.topic.p2p.authorize-debit}", groupId = "${kafka.topic.p2p.authorize-group}")
    public void listenDebitAuthorize(ConsumerRecord<String, String> consumerRecord) {
        debitConsumer.execute(consumerRecord);
    }

    @KafkaListener(id = "P2PAuthorizeCreditListener", topics = "${kafka.topic.p2p.authorize-credit}", groupId = "${kafka.topic.p2p.authorize-group}")
    public void listenCreditAuthorize(ConsumerRecord<String, String> consumerRecord) {
        creditConsumer.execute(consumerRecord);
    }
}
