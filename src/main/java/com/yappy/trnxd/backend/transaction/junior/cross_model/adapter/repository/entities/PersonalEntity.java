package com.yappy.trnxd.backend.transaction.junior.cross_model.adapter.repository.entities;

import com.yappy.trnxd.backend.transaction.junior.cross_model.enums.TransactionStatusEnum;
import com.yappy.trnxd.backend.transaction.junior.cross_model.enums.TransactionTypeEnum;
import com.yappy.trnxd.backend.transaction.junior.library.enums.ExecutionTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "payments")
@NoArgsConstructor
@AllArgsConstructor
public class PersonalEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 3637406431558008765L;

    @EmbeddedId
    PersonalEmbeddedDTO embeddedId;

    @Column(name = "amount", nullable = false, updatable = false)
    public BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "txn_type", nullable = false, updatable = false)
    public TransactionTypeEnum type;

    @Column(name = "bank_id", nullable = false, updatable = false)
    public String bankId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, updatable = false)
    public TransactionStatusEnum status;

    @Enumerated(EnumType.STRING)
    @Column(name = "execution_type", nullable = false, updatable = false)
    public ExecutionTypeEnum execution;
}
