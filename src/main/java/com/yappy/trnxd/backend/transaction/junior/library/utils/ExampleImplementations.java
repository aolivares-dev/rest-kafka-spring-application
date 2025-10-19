package com.yappy.trnxd.backend.transaction.junior.library.utils;

import com.yappy.trnxd.backend.transaction.junior.cross_model.adapter.repository.entities.PaymentEntity;

import java.util.List;

public interface ExampleImplementations {
    <T> Boolean isValidStatus(List<T> payments);
}
