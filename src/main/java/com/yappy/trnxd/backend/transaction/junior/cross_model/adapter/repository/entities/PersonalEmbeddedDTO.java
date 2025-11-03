package com.yappy.trnxd.backend.transaction.junior.cross_model.adapter.repository.entities;

import com.yappy.trnxd.backend.transaction.junior.cross_model.enums.OperationTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PersonalEmbeddedDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @Column(name = "cli_uuid", nullable = false, updatable = false)
    private String cliUuid;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation_type", nullable = false, updatable = false)
    private OperationTypeEnum operation;

}
