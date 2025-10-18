package com.yappy.trnxd.backend.transaction.junior.library.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CurrencyEnum {

    USD("USD", "Dolares Estadounidenses");

    private final String key;
    private final String message;
}
