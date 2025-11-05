package com.yappy.trnxd.backend.transaction.junior.cross_model.adapter.repository.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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
    public String id;

    @Column(name = "cli_uuid", nullable = false, updatable = false)
    public String cliUuid;

    @Column(name = "operation_type", nullable = false, updatable = false)
    public String operation;

}
