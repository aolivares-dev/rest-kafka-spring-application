package com.yappy.trnxd.backend.transaction.junior.cross_model.adapter.repository;

import com.yappy.trnxd.backend.transaction.junior.cross_model.adapter.repository.entities.PersonalEmbeddedDTO;
import com.yappy.trnxd.backend.transaction.junior.cross_model.adapter.repository.entities.PersonalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface P2PRepository extends JpaRepository<PersonalEntity, Long> {
    @Transactional(readOnly = true)
    @Query(value = """
              SELECT * FROM payments WHERE id = :paymentID
            """, nativeQuery = true)
    List<PersonalEntity> findTransactionsByID(@Param("paymentID") String paymentID);

    @Transactional(readOnly = true)
    @Query(value = """
              SELECT * FROM payments WHERE id = :paymentID
            """, nativeQuery = true)
    PersonalEntity findTransactionByID(@Param("paymentID") String paymentID);

    @Transactional(readOnly = true)
    @Query(value = """
              SELECT * FROM payments ypp
               WHERE ypp.cli_uuid = :#{#embeddedID.cliUuid}
               AND ypp.id = :#{#embeddedID.id}
               AND ypp.operation_type = :#{#embeddedID.operation}
            """, nativeQuery = true)
    PersonalEntity findByEmbeddedID(@Param("embeddedID") PersonalEmbeddedDTO embeddedID);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = """
              UPDATE payments ypp SET status = :status
              WHERE ypp.id = :#{#embeddedID.id}
               AND ypp.operation_type = :#{#embeddedID.operation}
               AND ypp.cli_uuid = :#{#embeddedID.cliUuid}
            """, nativeQuery = true)
    void updateTransactionStatus(@Param("embeddedID") PersonalEmbeddedDTO embeddedID, @Param("status") String status);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = """
              UPDATE payments ypp SET status = :status
              WHERE ypp.id = :transactionID
            """, nativeQuery = true)
    void updateInternalTransactionStatus(@Param("transactionID") String transactionID, @Param("status") String status);
}
