package com.yappy.trnxd.backend.transaction.junior.library.command;

import com.yappy.trnxd.backend.transaction.junior.library.model.ProfileDTO;

public abstract class CommandTemplate<REQUEST, RESPONSE> {

    public abstract RESPONSE execute(REQUEST request, ProfileDTO profile);

    public abstract RESPONSE executeFallback(REQUEST request, ProfileDTO profile);
}