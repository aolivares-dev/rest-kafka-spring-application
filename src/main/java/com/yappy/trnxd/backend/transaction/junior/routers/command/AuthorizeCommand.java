package com.yappy.trnxd.backend.transaction.junior.routers.command;

import com.yappy.trnxd.backend.transaction.junior.cores.personal_payment.logic.BeginTransactionLogic;
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
import com.yappy.trnxd.backend.transaction.junior.library.utils.BeginTransactionValidationUtils;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
@Service("AuthorizeCommand")
public class AuthorizeCommand extends CommandTemplate<TransactionRequestEntity<BeginTransactionDTO>, TransactionResponseEntity<BeginTransactionDTO>> {
    private final String COMMAND_NAME = "AuthorizeCommand";

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
    protected BeginTransactionValidationUtils beginTransactionValidationUtils;

    @Override
    @CircuitBreaker(name = COMMAND_NAME, fallbackMethod = "executeFallback")
    public TransactionResponseEntity<BeginTransactionDTO> execute(TransactionRequestEntity<BeginTransactionDTO> request, ProfileDTO profile) {
        var methodName = "execute";
        var transactionResponseEntity = new TransactionResponseEntity<BeginTransactionDTO>();
        var status = new StatusDTO();
        try {

            if (beginTransactionValidationUtils.bodyRequireValidation(request.getBody())) {
                status.setCode(BCStatusEnum.MANDATORY_FIELDS_MISSING.getCode());
                status.setMessage(BCStatusEnum.MANDATORY_FIELDS_MISSING.getMessage());
                transactionResponseEntity.setStatus(status);
                return transactionResponseEntity;
            }

            switch (TransactionTypeEnum.valueOf(request.getBody().getType())) {
                case P2P:
                    p2pLogic.execute(request.getBody());
                    break;
                case P2M:
                    p2mLogic.execute(request.getBody());
                    break;
                case M2P:
                    m2pLogic.execute(request.getBody());
                    break;
                case M2M:
                    m2mLogic.execute(request.getBody());
                    break;
                default:
                    throw new BusinessCapabilityException("NOT_IMPLEMENTED", "Not Implemented");
            }

            transactionResponseEntity.setBody(request.getBody());
            transactionResponseEntity.setProfile(profile);
            status.setCode(BCStatusEnum.SUCCESS.getCode());
            status.setMessage(BCStatusEnum.SUCCESS.getMessage());

        } catch (BusinessCapabilityException businessCapabilityException) {
            status.setCode(businessCapabilityException.getCode());
            status.setMessage(businessCapabilityException.getMessage());
            log.warn(businessCapabilityException.getMessage());

        } catch (Exception e) {
            status.setCode("500");
            status.setMessage(e.getMessage());
            log.info(LogCatalogEnum.PREFIX_EXCEPTION_MESSAGE.getMessage(), e.getMessage(), e);
        }

        transactionResponseEntity.setStatus(status);

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