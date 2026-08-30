package com.example.aop;

import org.springframework.stereotype.Component;

@Component
public class EspressoMachine implements CoffeeMachine {
    @Override
    public void brew() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Brewing coffee with Espresso Machine");
    }
}
