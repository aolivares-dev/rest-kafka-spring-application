package com.yappy.trnxd.backend.transaction.junior.library.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MerchantDebitorDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -9186552439735685567L;

    private String phoneNumber;

    private String token;

    private String account;

    private String alias;

    private String bank;
}
