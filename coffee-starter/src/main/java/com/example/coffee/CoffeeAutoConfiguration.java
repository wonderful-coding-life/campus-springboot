package com.example.coffee;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@AutoConfiguration
@ConditionalOnMissingBean(CoffeeMachine.class)
public class CoffeeAutoConfiguration {

    @Bean
    @Primary
    public CoffeeMachine dripCoffeeMachine() {
        return new DripCoffeeMachine();
    }

    @Bean
    public CoffeeMachine espressoMachine() {
        return new EspressoMachine();
    }

    @Bean
    public CoffeeMachine mochaCoffeeMachine() {
        return new MochaCoffeeMachine();
    }
}
