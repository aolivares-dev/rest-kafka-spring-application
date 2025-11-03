package com.yappy.trnxd.backend.transaction.junior.library.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.yappy.trnxd.backend.transaction.junior.library.enums.ClientTypeEnum;
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
public class ClientDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -9186552439735685567L;

    //  ALL CLIENTS
    @Schema(name = "cli_uuid", requiredMode = Schema.RequiredMode.REQUIRED, description = "UUID of the client", example = "")
    private String cliUuid;

    @Schema(name = "token", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "Token of the client", example = "")
    private String token;

    @Schema(name = "client_type", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "Type of the account", example = "PERSONAL, COMMERCIAL")
    private ClientTypeEnum clientType;

    @Schema(name = "bank_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "Bank ID of the client", example = "")
    private String bankId;

    //  PERSONAL CLIENTS ONLY
    @Schema(name = "name", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "Name of the client", example = "")
    private String name;

    @Schema(name = "phone_number", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "Phone number of the client", example = "555-5555")
    private String phoneNumber;

    //  BUSINESS CLIENTS ONLY
    @Schema(name = "alias", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "Name of the client", example = "")
    private String alias;

    @Schema(name = "metadata", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "Metadata of the client", example = "")
    private String metadata;
}
