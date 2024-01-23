package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    private InitService initService;

    @Override
    public void run(String... args) throws Exception {
        initService.initAdmin();
    }
}
