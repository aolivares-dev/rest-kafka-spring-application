package com.yappy.trnxd.backend.transaction.junior.cross_model.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.yappy.trnxd.backend.transaction.junior.library.model.ChargeAmountDTO;
import com.yappy.trnxd.backend.transaction.junior.library.model.ClientDTO;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class TransactionDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -9186552439735685567L;

    @Schema(name = "transaction_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "Id of the transaction", example = "998edd8a-3240-4dc7-aae9-47fa30269381")
    private String transactionId;

    @Schema(name = "debitor", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "Debitor of the transaction")
    private ClientDTO debitor;

    @Schema(name = "creditor", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "Creditor of the transaction")
    private ClientDTO creditor;

    @Schema(name = "charge_amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "Charge amount of the transaction")
    private ChargeAmountDTO chargeAmount;

    @Schema(name = "type", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "Type of the transaction", example = "P2P")
    private String type;

    @Schema(name = "execution", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "Execution method of the transaction", example = "INTERNAL, EXTERNAL")
    private String execution;

    @Schema(name = "operation", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "Execution method of the transaction", example = "DEBIT, CREDIT")
    private String operation;

    @Schema(name = "status", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "Status of the transaction", example = "PENDING, COMPLETED, CANCELLED")
    private String status;
}
