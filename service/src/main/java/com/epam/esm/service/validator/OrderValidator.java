package com.epam.esm.service.validator;

import org.springframework.stereotype.Component;

@Component
public class OrderValidator {
    private static final String INTEGER_REGEX_PATTERN = "\\d+";

    public void validateBalance(double balance, double price) throws ValidatorException{
        if(balance <= price){
            throw new ValidatorException("Not enough money to pay");
        }
    }

    public void validateId(Long id) throws ValidatorException {
        if (id == null || !id.toString().matches(INTEGER_REGEX_PATTERN)) {
            throw new ValidatorException("Id failed validation");
        }
    }
}
