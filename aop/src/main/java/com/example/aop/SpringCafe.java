package com.example.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SpringCafe implements ApplicationRunner {
    @Autowired
    private Barista barista;

    @Override
    @PrintExecutionTime
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 0; i < 10; i++) {
            barista.makeCoffees();
        }
    }
}
