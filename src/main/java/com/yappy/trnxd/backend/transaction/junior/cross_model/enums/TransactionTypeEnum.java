package com.yappy.trnxd.backend.transaction.junior.cross_model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TransactionTypeEnum {
    P2P("P2P", "PERSONAL TO PERSONAL"),
    P2M("P2M", "PERSONAL TO MERCHANT"),
    M2M("M2M", "MERCHANT TO MERCHANT"),
    M2P("M2P", "MERCHANT TO PERSONAL");

    private final String key;
    private final String message;
}
