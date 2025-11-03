package com.yappy.trnxd.backend.transaction.junior.library.utils;

import com.yappy.trnxd.backend.transaction.junior.cross_model.adapter.repository.P2PRepository;
import com.yappy.trnxd.backend.transaction.junior.cross_model.model.BeginTransactionDTO;
import com.yappy.trnxd.backend.transaction.junior.library.enums.BCStatusEnum;
import com.yappy.trnxd.backend.transaction.junior.library.model.ChargeAmountDTO;
import com.yappy.trnxd.backend.transaction.junior.library.model.ClientDTO;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ExecuteTransactionValidationUtils {

    @Value("${transaction.min.amount}")
    private BigDecimal MIN_AMOUNT;

    @Value("${transaction.max.amount}")
    private BigDecimal MAX_AMOUNT;

    @Autowired
    protected P2PRepository p2pRepository;

    public BCStatusEnum validationOfAmounts(ChargeAmountDTO chargeAmount) {
        var status = BCStatusEnum.SUCCESS;

        if (ObjectUtils.isEmpty(chargeAmount) || ObjectUtils.isEmpty(chargeAmount.getAmount())) {
            return BCStatusEnum.INVALID_AMOUNT;
        }

        var amount = chargeAmount.getAmount();

        if (amount.compareTo(MIN_AMOUNT) < 0) {
            return BCStatusEnum.AMOUNT_LESS_THAN_MINIMUM;
        }

        if (amount.compareTo(MAX_AMOUNT) > 0) {
            return BCStatusEnum.AMOUNT_GREATER_THAN_MAXIMUM;
        }

        return status;
    }

    public BCStatusEnum validationForPersonalClients(ClientDTO debitor, ClientDTO creditor) {
        var status = BCStatusEnum.SUCCESS;

        if (debitor.getCliUuid().equals(creditor.getCliUuid())) {
            return BCStatusEnum.SAME_CLIENT_NOT_ALLOWED;
        }

        return status;
    }

    public BCStatusEnum validationForDuplication(BeginTransactionDTO transaction) {
        var status = BCStatusEnum.SUCCESS;

        var transactionList = p2pRepository.findTransactionsByID(transaction.getTransactionId());

        if (ObjectUtils.isNotEmpty(transactionList)) {
            return BCStatusEnum.DUPLICATE_TRANSACTION;
        }

        return status;
    }
}
