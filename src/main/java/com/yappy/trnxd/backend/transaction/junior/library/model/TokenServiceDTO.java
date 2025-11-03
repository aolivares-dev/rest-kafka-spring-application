package com.yappy.trnxd.backend.transaction.junior.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TokenServiceDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String token;

    private String type;

    private String service;

    private String user;
}
