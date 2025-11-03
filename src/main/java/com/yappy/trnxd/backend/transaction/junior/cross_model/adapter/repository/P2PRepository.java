package com.yappy.trnxd.backend.transaction.junior.cross_model.adapter.repository;

import com.yappy.trnxd.backend.transaction.junior.cross_model.adapter.repository.entities.PersonalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface P2PRepository extends JpaRepository<PersonalEntity, Long> {
    @Transactional(readOnly = true)
    @Query(value = """
              SELECT * FROM yappy_payment.payments WHERE id = :paymentID
            """, nativeQuery = true)
    List<PersonalEntity> findTransactionsByID(@Param("paymentID") String paymentID);

    @Transactional(readOnly = true)
    @Query(value = """
              SELECT * FROM yappy_payment.payments WHERE id = :paymentID
            """, nativeQuery = true)
    PersonalEntity findTransactionByID(@Param("paymentID") String paymentID);
}
