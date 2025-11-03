package com.yappy.trnxd.backend.transaction.junior.library.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BCStatusEnum {

    SUCCESS("TXN-000", "Transaction Successful", false),
    FAILED("TXN-001", "Transaction Failed", true),
    IN_PROGRESS("TXN-002", "Transaction In Progress", false),
    CANCELLED("TXN-003", "Transaction Cancelled", true),
    UNKNOWN("TXN-004", "Unknown Error", true),
    TIMEOUT("TXN-005", "Transaction Timeout", true),
    INVALID_REQUEST("TXN-006", "Invalid Request", true),
    INVALID_AMOUNT("TXN-007", "Invalid Amount", true),
    AMOUNT_LESS_THAN_MINIMUM("TXN-008", "Amount Less Than Minimum", true),
    AMOUNT_GREATER_THAN_MAXIMUM("TXN-009", "Amount Greater Than Maximum", true),
    SAME_CLIENT_NOT_ALLOWED("TXN-010", "Same Client Not Allowed", true),
    DUPLICATE_TRANSACTION("TXN-011", "Duplicate Transaction", true),
    TOKEN_GENERATION_FAILED("TXN-012", "Token Generation Failed", true),
    MANDATORY_FIELDS_MISSING("TXN-013", "Mandatory Fields Missing", true);

    private final String code;
    private final String message;
    private final Boolean breakWorkflow;
}
