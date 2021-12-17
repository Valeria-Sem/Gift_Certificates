package com.epam.esm.service.validator;

import org.springframework.stereotype.Component;

@Component
public class OrderValidator {
    public void validateBalance(double balance, double price) throws ValidatorException{
        if(balance <= price){
            throw new ValidatorException("Not enough money to pay");
        }
    }
}
