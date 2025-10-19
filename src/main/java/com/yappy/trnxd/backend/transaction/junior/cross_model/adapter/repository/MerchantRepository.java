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
            INSERT INTO businesses (
                id,
                cli_uuid,
                amount,
                txn_type,
                status,
                operation_type
            )
            VALUES (
                JSON_UNQUOTE(JSON_EXTRACT(:jsonData, '$.id')),
                JSON_UNQUOTE(JSON_EXTRACT(:jsonData, '$.cli_uuid')),
                CAST(JSON_UNQUOTE(JSON_EXTRACT(:jsonData, '$.amount')) AS DECIMAL(10,2)),
                JSON_UNQUOTE(JSON_EXTRACT(:jsonData, '$.txn_type')),
                JSON_UNQUOTE(JSON_EXTRACT(:jsonData, '$.status')),
                JSON_UNQUOTE(JSON_EXTRACT(:jsonData, '$.operation_type'))
            )
            """, nativeQuery = true)
    void saveTransaction(@Param("jsonData") String jsonData);


}
