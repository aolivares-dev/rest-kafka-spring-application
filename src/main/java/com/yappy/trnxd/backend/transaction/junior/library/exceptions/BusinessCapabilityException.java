package com.yappy.trnxd.backend.transaction.junior.library.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BusinessCapabilityException extends RuntimeException {

    private final String code;

    public BusinessCapabilityException(String message, String code) {
        super(message);
        this.code = code;
    }

    public BusinessCapabilityException(String message, String code, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
