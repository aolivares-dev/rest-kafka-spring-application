package com.yappy.trnxd.backend.transaction.junior.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StatusDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -5762341682799768400L;

    private String code;
    private String message;
}
