package com.yappy.trnxd.backend.transaction.junior.cross_model.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.yappy.trnxd.backend.transaction.junior.library.model.PersonCreditorDTO;
import com.yappy.trnxd.backend.transaction.junior.library.model.PersonDebitorDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BeginP2PTransactionDTO extends TransactionDTO<PersonDebitorDTO, PersonCreditorDTO> {

}
