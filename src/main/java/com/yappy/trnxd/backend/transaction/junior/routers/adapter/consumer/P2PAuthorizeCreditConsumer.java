package com.yappy.trnxd.backend.transaction.junior.routers.adapter.consumer;


import com.yappy.trnxd.backend.transaction.junior.cross_model.enums.OperationTypeEnum;
import com.yappy.trnxd.backend.transaction.junior.cross_model.model.BeginTransactionDTO;
import com.yappy.trnxd.backend.transaction.junior.cross_model.model.TransactionRequestEntity;
import com.yappy.trnxd.backend.transaction.junior.cross_model.model.TransactionResponseEntity;
import com.yappy.trnxd.backend.transaction.junior.library.adapter.consumer.ConsumerTemplate;
import com.yappy.trnxd.backend.transaction.junior.library.command.CommandTemplate;
import com.yappy.trnxd.backend.transaction.junior.library.config.KafkaTopicConfiguration;
import com.yappy.trnxd.backend.transaction.junior.routers.command.AuthorizeCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component("P2PAuthorizeCreditConsumer")
public class P2PAuthorizeCreditConsumer extends ConsumerTemplate<BeginTransactionDTO> {

    @Autowired
    protected KafkaTopicConfiguration kafkaTopicConfiguration;

    @Autowired
    protected AuthorizeCommand command;

    @Override
    protected String getTopicError() {
        return kafkaTopicConfiguration.getP2pError();
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