package com.bi.recordmangement.services;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import io.swagger.annotations.Api;

@Component
@Api(value = "Get Message according to locale")

public class MessageByLocaleServiceImpl implements MessageByLocaleService {
    private static final String GEN_ERROR = "Some error occured";
    @Autowired
    private MessageSource messageSource;

    @Override
    public String getMessage(String id) {
        return getMessage(id, null);
    }

    @Override
    public String getMessage(String id, String[] args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(id, args, GEN_ERROR, locale);
    }

}
