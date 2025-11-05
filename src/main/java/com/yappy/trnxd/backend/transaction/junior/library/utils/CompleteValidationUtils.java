package com.yappy.trnxd.backend.transaction.junior.library.utils;

import com.yappy.trnxd.backend.transaction.junior.cross_model.adapter.repository.P2PRepository;
import com.yappy.trnxd.backend.transaction.junior.cross_model.adapter.repository.entities.PersonalEntity;
import com.yappy.trnxd.backend.transaction.junior.cross_model.enums.OperationTypeEnum;
import com.yappy.trnxd.backend.transaction.junior.cross_model.enums.TransactionStatusEnum;
import com.yappy.trnxd.backend.transaction.junior.cross_model.enums.TransactionTypeEnum;
import com.yappy.trnxd.backend.transaction.junior.cross_model.mappers.EntitiesMapper;
import com.yappy.trnxd.backend.transaction.junior.cross_model.model.BeginTransactionDTO;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompleteValidationUtils {

    @Autowired
    protected P2PRepository p2pRepository;

    @Autowired
    protected P2PRepository m2mRepository;

    @Autowired
    protected EntitiesMapper entitiesMapper;

    public void updateToCompleteStatus(BeginTransactionDTO transaction) {
        switch (TransactionTypeEnum.valueOf(transaction.getType())) {
            case P2P -> {
                var fetchingData = p2pRepository.findByEmbeddedID(updateP2PCompleteStatus(transaction).getEmbeddedId());
                if (ObjectUtils.isEmpty(fetchingData)) {
                    throw new RuntimeException("No data found for transaction id: " + transaction.getTransactionId());
                }

                fetchingData.setStatus(TransactionStatusEnum.valueOf(transaction.getStatus()));
                p2pRepository.updateTransactionStatus(fetchingData.getEmbeddedId(), fetchingData.getStatus().getKey());
            }
            case M2M -> {
                var fetchingData = m2mRepository.findByEmbeddedID(updateP2PCompleteStatus(transaction).getEmbeddedId());

                if (ObjectUtils.isEmpty(fetchingData)) {
                    throw new RuntimeException("No data found for transaction id: " + transaction.getTransactionId());
                }

                fetchingData.setStatus(TransactionStatusEnum.valueOf(transaction.getStatus()));
                m2mRepository.updateTransactionStatus(fetchingData.getEmbeddedId(), fetchingData.getStatus().getKey());
            }
        }
    }

    public PersonalEntity updateP2PCompleteStatus(BeginTransactionDTO transaction) {
        var entity = new PersonalEntity();

        if (OperationTypeEnum.DEBIT.getKey().equals(transaction.getOperation())) {
            entity = entitiesMapper.toDebitEntity(transaction);
        }

        if (OperationTypeEnum.CREDIT.getKey().equals(transaction.getOperation())) {
            entity = entitiesMapper.toCreditEntity(transaction);
        }

        return entity;
    }
}
