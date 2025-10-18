package com.yappy.trnxd.backend.transaction.junior.library.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LogCatalogEnum {

    FS_SUCCESS("send firestore success"),
    MESSAGE_ERROR("Se ha presentado el siguiente error"),
    PREFIX_INITIAL_MESSAGE("Inicio de "),
    PREFIX_END_MESSAGE("Fin de "),
    LOG_REQUEST_TEMPLATE("Request: {}"),
    LOG_RESPONSE_TEMPLATE("Response: {}"),
    PREFIX_EXCEPTION_MESSAGE("Se ha presentado la siguiente excepcion "),
    PREFIX_FALLBACK_EXCEPTION_MESSAGE("Se ha ejecutado el metodo fallback con la siguiente excepcion "),
    PREFIX_TIME_LIMITER_EXCEPTION_MESSAGE("Se ha ejecutado el metodo fallback por TimeLimiter "),
    PREFIX_BUSINESS_CAPABILITY_EXCEPTION_MESSAGE("Se ha presentado el siguiente business capability exception ");

    private final String message;

}
