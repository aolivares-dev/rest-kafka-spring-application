package com.yappy.trnxd.backend.transaction.junior.cross_model.adapter.repository;

import com.yappy.trnxd.backend.transaction.junior.cross_model.adapter.repository.entities.BusinessEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface P2PRepository {
    @Transactional(readOnly = true)
    @Query(value = """
              SELECT * FROM yappy_payment.payments WHERE id = :paymentID
            """, nativeQuery = true)
    List<BusinessEntity> findTransactionsByID(@Param("paymentID") String paymentID);

    @Transactional(readOnly = true)
    @Query(value = """
              SELECT * FROM yappy_payment.payments WHERE id = :paymentID
            """, nativeQuery = true)
    BusinessEntity findTransactionByID(@Param("paymentID") String paymentID);
}
