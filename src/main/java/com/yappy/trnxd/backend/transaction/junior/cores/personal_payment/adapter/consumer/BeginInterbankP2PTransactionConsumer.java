package com.yappy.trnxd.backend.transaction.junior.cores.personal_payment.adapter.consumer;


import com.yappy.trnxd.backend.transaction.junior.cross_model.model.BeginP2PTransactionDTO;
import com.yappy.trnxd.backend.transaction.junior.cross_model.model.TransactionRequestEntity;
import com.yappy.trnxd.backend.transaction.junior.cross_model.model.TransactionResponseEntity;
import com.yappy.trnxd.backend.transaction.junior.library.adapter.consumer.ConsumerTemplate;
import com.yappy.trnxd.backend.transaction.junior.library.command.CommandTemplate;
import com.yappy.trnxd.backend.transaction.junior.library.config.KafkaTopicConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Slf4j
@Component("BeginInterbankP2PTransactionConsumer")
public class BeginInterbankP2PTransactionConsumer extends ConsumerTemplate<BeginP2PTransactionDTO> {

    @Autowired
    protected KafkaTopicConfiguration kafkaTopicConfiguration;

    @Autowired
    @Qualifier("BeginInterbankP2PTransactionCommand")
    protected CommandTemplate<TransactionRequestEntity<BeginP2PTransactionDTO>, TransactionResponseEntity<BeginP2PTransactionDTO>> command;

    @Override
    protected String getErrorTopicName() {
        return kafkaTopicConfiguration.getInterP2PBeginError();
    }

    @Override
    protected CommandTemplate<TransactionRequestEntity<BeginP2PTransactionDTO>, TransactionResponseEntity<BeginP2PTransactionDTO>> getCommand() {
        return command;
    }
}