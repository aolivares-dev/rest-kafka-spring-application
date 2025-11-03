package com.yappy.trnxd.backend.transaction.junior.cross_model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TransactionStatusEnum {
    COMPLETED("COMPLETED"),
    FAILED("FAILED"),
    PENDING("PENDING"),
    IN_TRANSIT("IN_TRANSIT");

    private final String key;

    public boolean isNotEqual(TransactionStatusEnum status) {
        return !this.equals(status);
    }
}
