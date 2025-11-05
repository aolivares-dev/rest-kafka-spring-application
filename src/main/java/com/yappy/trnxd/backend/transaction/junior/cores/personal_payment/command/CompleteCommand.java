package com.yappy.trnxd.backend.transaction.junior.cores.personal_payment.command;

import com.yappy.trnxd.backend.transaction.junior.cores.personal_payment.adapter.producer.P2PAuthorizeCreditProducer;
import com.yappy.trnxd.backend.transaction.junior.cores.personal_payment.adapter.producer.P2PErrorProducer;
import com.yappy.trnxd.backend.transaction.junior.cores.personal_payment.logic.BeginTransactionLogic;
import com.yappy.trnxd.backend.transaction.junior.cross_model.enums.OperationTypeEnum;
import com.yappy.trnxd.backend.transaction.junior.cross_model.enums.TransactionStatusEnum;
import com.yappy.trnxd.backend.transaction.junior.cross_model.enums.TransactionTypeEnum;
import com.yappy.trnxd.backend.transaction.junior.cross_model.model.BeginTransactionDTO;
import com.yappy.trnxd.backend.transaction.junior.cross_model.model.TransactionRequestEntity;
import com.yappy.trnxd.backend.transaction.junior.cross_model.model.TransactionResponseEntity;
import com.yappy.trnxd.backend.transaction.junior.library.command.CommandTemplate;
import com.yappy.trnxd.backend.transaction.junior.library.enums.BCStatusEnum;
import com.yappy.trnxd.backend.transaction.junior.library.enums.LogCatalogEnum;
import com.yappy.trnxd.backend.transaction.junior.library.exceptions.BusinessCapabilityException;
import com.yappy.trnxd.backend.transaction.junior.library.model.ProfileDTO;
import com.yappy.trnxd.backend.transaction.junior.library.model.StatusDTO;
import com.yappy.trnxd.backend.transaction.junior.library.utils.BeginValidationUtils;
import com.yappy.trnxd.backend.transaction.junior.library.utils.CompleteValidationUtils;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
@Service("CompleteCommand")
public class CompleteCommand extends CommandTemplate<TransactionRequestEntity<BeginTransactionDTO>, TransactionResponseEntity<BeginTransactionDTO>> {
    private final String COMMAND_NAME = "CompleteCommand";

    @Autowired
    @Qualifier("P2PLogicImpl")
    private BeginTransactionLogic<BeginTransactionDTO> p2pLogic;

    @Autowired
    @Qualifier("P2PLogicImpl")
    private BeginTransactionLogic<BeginTransactionDTO> p2mLogic;

    @Autowired
    @Qualifier("P2PLogicImpl")
    private BeginTransactionLogic<BeginTransactionDTO> m2pLogic;

    @Autowired
    @Qualifier("P2PLogicImpl")
    private BeginTransactionLogic<BeginTransactionDTO> m2mLogic;

    @Autowired
    protected BeginValidationUtils beginValidationUtils;

    @Autowired
    protected CompleteValidationUtils completeValidationUtils;

    @Autowired
    protected P2PAuthorizeCreditProducer authorizeCreditProducer;

    @Autowired
    protected P2PErrorProducer errorProducer;

    @Override
    @CircuitBreaker(name = COMMAND_NAME, fallbackMethod = "executeFallback")
    public TransactionResponseEntity<BeginTransactionDTO> execute(TransactionRequestEntity<BeginTransactionDTO> request, ProfileDTO profile) {
        var methodName = "execute";
        var transactionResponseEntity = new TransactionResponseEntity<BeginTransactionDTO>();
        var status = new StatusDTO();
        try {

            if (beginValidationUtils.bodyRequireValidation(request.getBody())) {
                status.setCode(BCStatusEnum.MANDATORY_FIELDS_MISSING.getCode());
                status.setMessage(BCStatusEnum.MANDATORY_FIELDS_MISSING.getMessage());
                transactionResponseEntity.setStatus(status);
                return transactionResponseEntity;
            }

            var body = request.getBody();
            body.setStatus(TransactionStatusEnum.COMPLETED.getKey());
            completeValidationUtils.updateToCompleteStatus(body);

            transactionResponseEntity.setBody(body);
            transactionResponseEntity.setProfile(profile);
            status.setCode(BCStatusEnum.SUCCESS.getCode());
            status.setMessage(BCStatusEnum.SUCCESS.getMessage());

            if (OperationTypeEnum.DEBIT.getKey().equals(body.getOperation())) {
                authorizeCreditProducer.onSuccess(transactionResponseEntity);
            }

            log.info(LogCatalogEnum.PREFIX_END_MESSAGE.getMessage().concat(methodName));
            return transactionResponseEntity;
        } catch (BusinessCapabilityException businessCapabilityException) {
            status.setCode(businessCapabilityException.getCode());
            status.setMessage(businessCapabilityException.getMessage());
        } catch (Exception e) {
            status.setCode("500");
            status.setMessage(e.getMessage());
        }

        log.debug("Error en el completeCommand: {} - {}", status.getCode(), status.getMessage());
        transactionResponseEntity.setStatus(status);

        errorProducer.onFail(transactionResponseEntity);

        switch (TransactionTypeEnum.valueOf(request.getBody().getType())) {
            case P2P:
                p2pLogic.updateToFailedStatus(request.getBody());
                break;
            case P2M:
                p2mLogic.updateToFailedStatus(request.getBody());
                break;
            case M2P:
                m2pLogic.updateToFailedStatus(request.getBody());
                break;
            case M2M:
                m2mLogic.updateToFailedStatus(request.getBody());
                break;
        }


        log.info(LogCatalogEnum.PREFIX_END_MESSAGE.getMessage().concat(methodName));
        return transactionResponseEntity;
    }

    @Override
    public TransactionResponseEntity<BeginTransactionDTO> executeFallback(TransactionRequestEntity<BeginTransactionDTO> request, ProfileDTO profile) {

        BeginTransactionDTO data = request.getBody();
        log.warn("Fallback ejecutado para transacción P2P: {}", data);

        return new TransactionResponseEntity<>();
    }
}