package com.yappy.trnxd.backend.transaction.junior.library.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ClientTypeEnum {
    PERSONAL("P", "PERSONAL"),
    BUSINESS("B", "BUSINESS"),
    ENTREPRENEUR("E", "ENTREPRENEUR");

    private final String key;
    private final String value;
}
