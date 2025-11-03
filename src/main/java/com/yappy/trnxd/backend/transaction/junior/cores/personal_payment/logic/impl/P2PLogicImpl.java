package com.yappy.trnxd.backend.transaction.junior.cores.personal_payment.logic.impl;

import com.yappy.trnxd.backend.transaction.junior.cores.personal_payment.logic.BeginTransactionLogic;
import com.yappy.trnxd.backend.transaction.junior.cross_model.adapter.repository.P2PRepository;
import com.yappy.trnxd.backend.transaction.junior.cross_model.adapter.repository.entities.PersonalEntity;
import com.yappy.trnxd.backend.transaction.junior.cross_model.enums.OperationTypeEnum;
import com.yappy.trnxd.backend.transaction.junior.cross_model.enums.TransactionStatusEnum;
import com.yappy.trnxd.backend.transaction.junior.cross_model.mappers.EntitiesMapper;
import com.yappy.trnxd.backend.transaction.junior.cross_model.model.BeginTransactionDTO;
import com.yappy.trnxd.backend.transaction.junior.library.adapter.services.TransactionTokenService;
import com.yappy.trnxd.backend.transaction.junior.library.enums.BCStatusEnum;
import com.yappy.trnxd.backend.transaction.junior.library.exceptions.BusinessCapabilityException;
import com.yappy.trnxd.backend.transaction.junior.library.utils.ExecuteTransactionValidationUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("P2PLogicImpl")
public class P2PLogicImpl implements BeginTransactionLogic<BeginTransactionDTO> {

    @Autowired
    protected ExecuteTransactionValidationUtils validateTransactionUtils;

    @Autowired
    protected TransactionTokenService transactionTokenService;

    @Autowired
    protected P2PRepository p2pRepository;

    @Autowired
    protected EntitiesMapper entitiesMapper;

    @Override
    public void execute(BeginTransactionDTO transaction) {
        validateTransaction(transaction);

        saveTransaction(transaction);

        updateTransactionStatus(transaction);
    }

    private void validateTransaction(BeginTransactionDTO transaction) {

        var validationForPersonalClients = validateTransactionUtils.validationForPersonalClients(transaction.getDebitor(), transaction.getCreditor());

        if (!BCStatusEnum.SUCCESS.getCode().equals(validationForPersonalClients.getCode())) {
            throw new BusinessCapabilityException(validationForPersonalClients.getMessage(), validationForPersonalClients.getCode());
        }

        var validationOfAmounts = validateTransactionUtils.validationOfAmounts(transaction.getChargeAmount());

        if (!BCStatusEnum.SUCCESS.getCode().equals(validationOfAmounts.getCode())) {
            throw new BusinessCapabilityException(validationOfAmounts.getMessage(), validationOfAmounts.getCode());
        }

        var validationForDuplication = validateTransactionUtils.validationForDuplication(transaction);

        if (!BCStatusEnum.SUCCESS.getCode().equals(validationForDuplication.getCode())) {
            throw new BusinessCapabilityException(validationForDuplication.getMessage(), validationForDuplication.getCode());
        }

        var transactionToken = transactionTokenService.generateTransactionToken(transaction);

        if (ObjectUtils.isEmpty(transactionToken)) {
            throw new BusinessCapabilityException(BCStatusEnum.TOKEN_GENERATION_FAILED.getMessage(), BCStatusEnum.TOKEN_GENERATION_FAILED.getCode());
        }

        transaction.setDebitAuthorizeToken(transactionToken);
    }

    private void saveTransaction(BeginTransactionDTO transaction) {

        transaction.setStatus(TransactionStatusEnum.PENDING.getKey());

        var debitEntity = entitiesMapper.toDebitEntity(transaction);
        debitEntity.getEmbeddedId().setOperation(OperationTypeEnum.DEBIT);

        var creditEntity = entitiesMapper.toCreditEntity(transaction);
        creditEntity.getEmbeddedId().setOperation(OperationTypeEnum.CREDIT);

        p2pRepository.save(debitEntity);
        p2pRepository.save(creditEntity);
    }

    private void updateTransactionStatus(BeginTransactionDTO transaction) {
        var transactions = p2pRepository.findTransactionsByID(transaction.getTransactionId());

        var allowedStatuses = new ArrayList<String>();
        allowedStatuses.add(TransactionStatusEnum.PENDING.getKey());

        for (PersonalEntity payment : transactions) {
            if (allowedStatuses.contains(payment.getStatus().getKey())) {
                payment.setStatus(TransactionStatusEnum.IN_TRANSIT);
            }

            p2pRepository.save(payment);
        }
    }
}
