package com.bi.recordmanagement.config;

import javax.validation.constraints.NotNull;

public interface IAuthorizationExpression {
    boolean mayProceed();

    @NotNull
    String toHumanReadableExpression();
}
