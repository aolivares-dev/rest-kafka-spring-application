package com.yappy.trnxd.backend.transaction.junior.library.utils;

import com.yappy.trnxd.backend.transaction.junior.cross_model.model.BeginTransactionDTO;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

@Component
public class BeginValidationUtils {

    public boolean bodyRequireValidation(BeginTransactionDTO transactionDTO) {
        return ObjectUtils.isEmpty(transactionDTO.getType()) ||
                ObjectUtils.isEmpty(transactionDTO.getExecution()) ||
                ObjectUtils.isEmpty(transactionDTO.getTransactionId()) ||
                ObjectUtils.isEmpty(transactionDTO.getDebitor()) ||
                ObjectUtils.isEmpty(transactionDTO.getCreditor()) ||
                ObjectUtils.isEmpty(transactionDTO.getChargeAmount());
    }
}
