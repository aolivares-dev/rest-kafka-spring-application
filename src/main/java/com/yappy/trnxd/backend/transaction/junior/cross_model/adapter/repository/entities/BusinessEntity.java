package com.yappy.trnxd.backend.transaction.junior.cross_model.adapter.repository.entities;

import com.yappy.trnxd.backend.transaction.junior.cross_model.enums.OperationTypeEnum;
import com.yappy.trnxd.backend.transaction.junior.cross_model.enums.TransactionStatusEnum;
import com.yappy.trnxd.backend.transaction.junior.cross_model.enums.TransactionTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "businesses")
@NoArgsConstructor
@AllArgsConstructor
public class BusinessEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 3637406431558008765L;


    @Column(name = "id", nullable = false, updatable = false)
    @Id
    private String id;

    @Column(name = "client_uuid", nullable = false, updatable = false)
    private String clientUuid;

    @Column(name = "amount", nullable = false, updatable = false)
    private BigDecimal amount;

    @Column(name = "transaction_type", nullable = false, updatable = false)
    private TransactionTypeEnum transactionType;

    @Column(name = "status", nullable = false, updatable = false)
    private TransactionStatusEnum status;

    @Column(name = "operation_type", nullable = false, updatable = false)
    private OperationTypeEnum operationType;
}

