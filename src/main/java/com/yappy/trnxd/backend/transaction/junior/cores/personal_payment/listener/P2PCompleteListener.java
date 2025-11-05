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
@Component("P2PCompleteListener")
public class P2PCompleteListener {

    @Autowired
    @Qualifier("P2PCompleteDebitConsumer")
    private ConsumerTemplate<BeginTransactionDTO> debitConsumer;

    @Autowired
    @Qualifier("P2PCompleteCreditConsumer")
    private ConsumerTemplate<BeginTransactionDTO> creditConsumer;

    @KafkaListener(id = "CompleteDebitListener", topics = "${kafka.topic.p2p-complete-debit}", groupId = "${kafka.topic.p2p-complete-group}")
    public void listenDebitComplete(ConsumerRecord<String, String> consumerRecord) {
        debitConsumer.execute(consumerRecord);
    }

    @KafkaListener(id = "CompleteCreditListener", topics = "${kafka.topic.p2p-complete-credit}", groupId = "${kafka.topic.p2p-complete-group}")
    public void listenCreditComplete(ConsumerRecord<String, String> consumerRecord) {
        creditConsumer.execute(consumerRecord);
    }
}
