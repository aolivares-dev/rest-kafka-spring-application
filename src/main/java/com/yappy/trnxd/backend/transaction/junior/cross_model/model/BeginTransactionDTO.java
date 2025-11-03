package com.yappy.trnxd.backend.transaction.junior.cross_model.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.yappy.trnxd.backend.transaction.junior.library.model.TokenServiceDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BeginTransactionDTO extends TransactionDTO {

    @Serial
    private static final long serialVersionUID = -6477093641540539594L;

    @Schema(name = "payment_description", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "Payment for invoice #1234")
    private String paymentDescription;

    @Schema(name = "debit_authorize_token", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private TokenServiceDTO debitAuthorizeToken;
}
