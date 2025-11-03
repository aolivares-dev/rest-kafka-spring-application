package com.yappy.trnxd.backend.transaction.junior.cores.personal_payment.adapter.consumer;


import com.yappy.trnxd.backend.transaction.junior.cores.personal_payment.command.BeginCommand;
import com.yappy.trnxd.backend.transaction.junior.cross_model.enums.OperationTypeEnum;
import com.yappy.trnxd.backend.transaction.junior.cross_model.model.BeginTransactionDTO;
import com.yappy.trnxd.backend.transaction.junior.cross_model.model.TransactionRequestEntity;
import com.yappy.trnxd.backend.transaction.junior.cross_model.model.TransactionResponseEntity;
import com.yappy.trnxd.backend.transaction.junior.library.adapter.consumer.ConsumerTemplate;
import com.yappy.trnxd.backend.transaction.junior.library.command.CommandTemplate;
import com.yappy.trnxd.backend.transaction.junior.library.config.KafkaTopicConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component("P2PCompleteCreditConsumer")
public class P2PCompleteCreditConsumer extends ConsumerTemplate<BeginTransactionDTO> {

    @Autowired
    protected KafkaTopicConfiguration kafkaTopicConfiguration;

    @Autowired
    protected BeginCommand command;

    @Override
    protected String getErrorTopicName() {
        return kafkaTopicConfiguration.getP2pCompleteCreditTopic();
    }

    @Override
    protected Class<BeginTransactionDTO> getRequestType() {
        return BeginTransactionDTO.class;
    }

    @Override
    protected CommandTemplate<TransactionRequestEntity<BeginTransactionDTO>, TransactionResponseEntity<BeginTransactionDTO>> getCommand() {
        return command;
    }

    @Override
    protected void setDefaultValues(BeginTransactionDTO message) {
        message.setOperation(OperationTypeEnum.CREDIT.name());
    }
}