package com.yappy.trnxd.backend.transaction.junior.cross_model.mappers;

import com.yappy.trnxd.backend.transaction.junior.cross_model.adapter.repository.entities.PersonalEntity;
import com.yappy.trnxd.backend.transaction.junior.cross_model.model.BeginTransactionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EntitiesMapper {

    @Mapping(target = "embeddedId.id", source = "transactionId")
    @Mapping(target = "embeddedId.cliUuid", source = "debitor.cliUuid")
    @Mapping(target = "embeddedId.operation", source = "operation")
    @Mapping(target = "amount", source = "chargeAmount.amount")
    @Mapping(target = "execution", source = "execution")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "bankId", source = "debitor.bankId")
    PersonalEntity toDebitEntity(BeginTransactionDTO transactionDTO);

    @Mapping(target = "embeddedId.id", source = "transactionId")
    @Mapping(target = "embeddedId.cliUuid", source = "creditor.cliUuid")
    @Mapping(target = "embeddedId.operation", source = "operation")
    @Mapping(target = "amount", source = "chargeAmount.amount")
    @Mapping(target = "execution", source = "execution")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "bankId", source = "creditor.bankId")
    PersonalEntity toCreditEntity(BeginTransactionDTO transactionDTO);
}
