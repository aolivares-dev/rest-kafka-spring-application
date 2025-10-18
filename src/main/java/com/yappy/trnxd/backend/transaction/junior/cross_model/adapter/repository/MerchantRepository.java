package com.yappy.trnxd.backend.transaction.junior.cross_model.adapter.repository;

import com.yappy.trnxd.backend.transaction.junior.cross_model.adapter.repository.entities.BusinessEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MerchantRepository extends CrudRepository<BusinessEntity, Long> {

    @Query(value = """
              SELECT * FROM yappy_payment.businesses WHERE id = :businessID
            """, nativeQuery = true)
    List<BusinessEntity> findTransactionsByID(@Param("businessID") String businessID);



    @Transactional
    @Modifying
    @Query(value = """
            INSERT INTO yappy_payment.businesses (
                id,
                cli_uuid,
                amount,
                txn_type,
                status,
                operation_type
            ) VALUES (
                :BusinessEntity ->> 'id',
                'CLI789012',
                150.75,
                'DEBIT',
                'EXECUTED',
                'PURCHASE'
            );
            """, nativeQuery = true)
    void saveTransaction(@Param("iValue") String BusinessEntity);
}
