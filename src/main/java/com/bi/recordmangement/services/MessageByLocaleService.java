package com.bi.recordmangement.services;

import io.swagger.annotations.Api;

@Api(value = "Get Message according to locale")

public interface MessageByLocaleService {

    String getMessage(String id);

    String getMessage(String id, String... args);

}
