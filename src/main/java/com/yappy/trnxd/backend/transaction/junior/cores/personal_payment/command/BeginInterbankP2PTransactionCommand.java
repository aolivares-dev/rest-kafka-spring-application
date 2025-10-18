package com.yappy.trnxd.backend.transaction.junior.cores.personal_payment.command;

import com.yappy.trnxd.backend.transaction.junior.cross_model.model.BeginP2PTransactionDTO;
import com.yappy.trnxd.backend.transaction.junior.cross_model.model.TransactionRequestEntity;
import com.yappy.trnxd.backend.transaction.junior.cross_model.model.TransactionResponseEntity;
import com.yappy.trnxd.backend.transaction.junior.library.command.CommandTemplate;
import com.yappy.trnxd.backend.transaction.junior.library.model.ProfileDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("BeginInterbankP2PTransactionCommand")
public class BeginInterbankP2PTransactionCommand extends CommandTemplate<TransactionRequestEntity<BeginP2PTransactionDTO>, TransactionResponseEntity<BeginP2PTransactionDTO>> {
    private final String COMMAND_NAME = "BeginInterbankP2PTransactionCommand";

    @Override
    @CircuitBreaker(name = COMMAND_NAME, fallbackMethod = "executeFallback")
    public TransactionResponseEntity<BeginP2PTransactionDTO> execute(TransactionRequestEntity<BeginP2PTransactionDTO> request, ProfileDTO profile) {

        BeginP2PTransactionDTO data = request.getBody();
        log.info("Ejecutando lógica para transacción P2P: {}", data);

        return new TransactionResponseEntity<>();
    }

    @Override
    public TransactionResponseEntity<BeginP2PTransactionDTO> executeFallback(TransactionRequestEntity<BeginP2PTransactionDTO> request, ProfileDTO profile) {

        BeginP2PTransactionDTO data = request.getBody();
        log.warn("Fallback ejecutado para transacción P2P: {}", data);

        return new TransactionResponseEntity<>();
    }
}