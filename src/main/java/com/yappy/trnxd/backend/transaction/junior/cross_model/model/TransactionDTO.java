package com.yappy.trnxd.backend.transaction.junior.cross_model.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.yappy.trnxd.backend.transaction.junior.library.model.ChargeAmountDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TransactionDTO<DEBITOR, CREDITOR> implements Serializable  {
    @Serial
    private static final long serialVersionUID = -9186552439735685567L;

    private String transactionId;

    private DEBITOR debitor;

    private CREDITOR creditor;

    private ChargeAmountDTO chargeAmount;
}
