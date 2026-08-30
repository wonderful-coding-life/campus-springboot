package com.example.ioc;

import org.springframework.stereotype.Component;

@Component
public class MochaCoffeeMachine implements CoffeeMachine {
    @Override
    public void brew() {
        System.out.println("Brewing coffee with Mocha Coffee Machine");
    }
}
