package com.example.coffee;

public class DripCoffeeMachine implements CoffeeMachine {
    @Override
    public void brew() {
        System.out.println("Brewing coffee with Drip Coffee Machine");
    }
}
