package com.yappy.trnxd.backend.transaction.junior.library.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4984523979650652762L;

    @Schema(description = "Id del cliente", nullable = false, example = "998edd8a-3240-4dc7-aae9-47fa30269381")
    @JsonProperty("client_id")
    protected String clientId;

    @Schema(description = "Tipo", nullable = false, example = "P/C/E")
    @JsonProperty("client_type")
    protected String clientType;

    @Schema(description = "Id del usuario", nullable = false, example = "998edd8a-3240-4dc7-aae9-47fa30269381")
    @JsonProperty("user_id")
    protected String userId;

    @JsonProperty("bank_id")
    protected String bankId;

    @Schema(description = "Fecha", nullable = false, example = "2021-02-24T10:00:55.000Z")
    @JsonProperty("date")
    protected transient LocalDateTime date;
}
