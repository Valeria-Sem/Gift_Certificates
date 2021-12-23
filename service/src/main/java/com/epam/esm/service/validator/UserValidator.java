package com.epam.esm.service.validator;

import org.springframework.stereotype.Component;

@Component
public class UserValidator {
    private static final String LONG_REGEX_PATTERN = "\\d+";

    public void validateId(Long id) throws ValidatorException {
        if (id == null || !id.toString().matches(LONG_REGEX_PATTERN)) {
            throw new ValidatorException("Id failed validation");
        }
    }
}
