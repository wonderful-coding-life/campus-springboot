package com.example.ioc;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class DripCoffeeMachine implements CoffeeMachine {
    @Override
    public void brew() {
        System.out.println("Brewing coffee with Drip Coffee Machine");
    }
}
