package com.yappy.trnxd.backend.transaction.junior.cross_model.model;

import com.yappy.trnxd.backend.transaction.junior.library.model.ProfileDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestEntity<T> {
    private T body;

    private ProfileDTO profile;
}