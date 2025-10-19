package com.yappy.trnxd.backend.transaction.junior.library.utils;

import com.yappy.trnxd.backend.transaction.junior.library.enums.LogCatalogEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommonLogic {

    public <RESPONSE, REQUEST> RESPONSE fetchPaymentsByID(Function<REQUEST, RESPONSE> fetchFunction, REQUEST id) {
        String methodName = "fetchByDomain";

        try {
            return fetchFunction.apply(id);
        } catch (Exception e) {
            log.error(LogCatalogEnum.PREFIX_EXCEPTION_MESSAGE.getMessage(), e);
            return null;
        } finally {
            log.debug(LogCatalogEnum.PREFIX_END_MESSAGE.getMessage().concat(methodName));
        }
    }


    public <REQUEST> void saveTransaction(Consumer<REQUEST> fetchFunction, REQUEST object) {

        String methodName = "saveTransaction";

        try {
            fetchFunction.accept(object);
        } catch (Exception e) {
            log.error(LogCatalogEnum.PREFIX_EXCEPTION_MESSAGE.getMessage(), e);
        } finally {
            log.debug(LogCatalogEnum.PREFIX_END_MESSAGE.getMessage().concat(methodName));
        }
    }

}
