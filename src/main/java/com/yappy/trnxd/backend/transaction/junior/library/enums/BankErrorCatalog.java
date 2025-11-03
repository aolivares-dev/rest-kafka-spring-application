package com.yappy.trnxd.backend.transaction.junior.library.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BankErrorCatalog {
    BANK_ERROR("BANK-001", "Error en el banco", true),
    INSUFFICIENT_FUNDS("BANK-002", "Saldo insuficiente", true);

    private final String code;
    private final String message;
    private final Boolean breakWorkflow;
}
