package com.yappy.trnxd.backend.transaction.junior.cross_model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OperationTypeEnum {
    DEBIT("DEBIT", "Debit Operation"),
    CREDIT("CREDIT", "Credit Operation");

    private final String key;
    private final String description;
}
