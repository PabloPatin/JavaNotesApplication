package com.app;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.TimeZone;

@Configuration
public class LocaleConfig {
    @PostConstruct
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Moscow"));
    }}
