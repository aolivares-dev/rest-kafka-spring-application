package com.yappy.trnxd.backend.transaction.junior.cross_model.model;

import com.yappy.trnxd.backend.transaction.junior.library.model.ProfileDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestEntity<T> {
    private T body;

    @Schema(name = "profile", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "Profile information")
    private ProfileDTO profile;
}