package com.example.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class Barista {
    @Autowired
    private CoffeeMachine coffeeMachine;

    @Async
    public void makeCoffees() {
        coffeeMachine.brew();
    }
}
