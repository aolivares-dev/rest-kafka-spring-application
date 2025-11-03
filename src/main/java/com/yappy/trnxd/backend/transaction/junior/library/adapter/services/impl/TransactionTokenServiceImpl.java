package com.yappy.trnxd.backend.transaction.junior.library.adapter.services.impl;

import com.yappy.trnxd.backend.transaction.junior.cross_model.model.BeginTransactionDTO;
import com.yappy.trnxd.backend.transaction.junior.library.adapter.services.TransactionTokenService;
import com.yappy.trnxd.backend.transaction.junior.library.enums.LogCatalogEnum;
import com.yappy.trnxd.backend.transaction.junior.library.model.TokenServiceDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.UUID;

@Slf4j
@Service("TransactionTokenServiceImpl")
public class TransactionTokenServiceImpl implements TransactionTokenService {

    @Override
    public TokenServiceDTO generateTransactionToken(BeginTransactionDTO transactionDTO) {
        var methodName = "generateTransactionToken";

        if (ObjectUtils.isEmpty(transactionDTO.getPaymentDescription())) {
            transactionDTO.setPaymentDescription("GENERATE_TOKEN");
        }

        if (noGenerateToken(transactionDTO.getPaymentDescription())) {
            return null;
        }

        var tokenServiceDTO = new TokenServiceDTO();
        tokenServiceDTO.setToken(UUID.randomUUID().toString());
        tokenServiceDTO.setType(transactionDTO.getType());
        tokenServiceDTO.setUser(transactionDTO.getDebitor().getCliUuid());
        tokenServiceDTO.setService(transactionDTO.getExecution().concat("-".concat(transactionDTO.getType())));

        log.info(LogCatalogEnum.PREFIX_END_MESSAGE.getMessage().concat(methodName));
        return tokenServiceDTO;
    }

    private Boolean noGenerateToken(String description) {
        var notGenerationDescription = new HashSet<String>();
        notGenerationDescription.add("NOT_GENERATE_TOKEN");
        notGenerationDescription.add("SKIP_TOKEN_CREATION");

        if (notGenerationDescription.contains(description)) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }
}
