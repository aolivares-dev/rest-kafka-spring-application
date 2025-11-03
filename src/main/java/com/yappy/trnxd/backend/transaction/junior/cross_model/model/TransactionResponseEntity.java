package com.yappy.trnxd.backend.transaction.junior.cross_model.model;

import com.yappy.trnxd.backend.transaction.junior.library.model.StatusDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TransactionResponseEntity<T> extends TransactionRequestEntity<T> {
    private StatusDTO status;
}
