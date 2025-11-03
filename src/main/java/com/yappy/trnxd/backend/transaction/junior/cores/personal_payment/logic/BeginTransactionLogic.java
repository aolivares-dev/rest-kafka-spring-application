package com.yappy.trnxd.backend.transaction.junior.cores.personal_payment.logic;

public interface BeginTransactionLogic<T> {

    void execute(T transaction);

}
