package com.example.service;

import com.example.enums.LangEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
@Service
public class ResourceBundleService {
    @Autowired
    private ResourceBundleMessageSource resourceBundleMessageSource;

    public  String getMessage(String code, LangEnum appLanguage) {
        return resourceBundleMessageSource.getMessage(code, null, new Locale(appLanguage.name()));
    }

}
