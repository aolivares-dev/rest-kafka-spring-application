package com.yappy.trnxd.backend.transaction.junior.cross_model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TransactionStatusEnum {
    COMPLETED, FAILED, IN_PROGRESS, CANCELLED;
}
