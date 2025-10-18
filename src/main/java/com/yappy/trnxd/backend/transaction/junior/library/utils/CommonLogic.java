package com.yappy.trnxd.backend.transaction.junior.library.utils;

import com.yappy.trnxd.backend.transaction.junior.library.enums.LogCatalogEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Slf4j
@Component("CommonLogic")
@RequiredArgsConstructor
public abstract class CommonLogic {

    public <T> List<T> fetchPaymentsByID(Function<String, List<T>> fetchFunction, String id) {
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


    public void saveTransaction(Function<String, Void> fetchFunction, String id) {

        String methodName = "saveTransaction";

        try {
            fetchFunction.apply(id);
        } catch (Exception e) {
            log.error(LogCatalogEnum.PREFIX_EXCEPTION_MESSAGE.getMessage(), e);
        } finally {
            log.debug(LogCatalogEnum.PREFIX_END_MESSAGE.getMessage().concat(methodName));
        }
    }

}
