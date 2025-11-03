package com.yappy.trnxd.backend.transaction.junior.library.adapter.services;

import com.yappy.trnxd.backend.transaction.junior.cross_model.model.BeginTransactionDTO;
import com.yappy.trnxd.backend.transaction.junior.library.model.TokenServiceDTO;

public interface TransactionTokenService {
    TokenServiceDTO generateTransactionToken(BeginTransactionDTO transactionDTO);
}
