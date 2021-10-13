package com.epam.esm.service.validator;

public class  ValidatorFactory {
    private final TagValidator tagValidator;

    private ValidatorFactory() {
        tagValidator = new TagValidator();
    }

    private static class ValidatorFactorySingletonHolder {
        static final ValidatorFactory INSTANCE = new ValidatorFactory();
    }

    public static ValidatorFactory getInstance() {
        return ValidatorFactorySingletonHolder.INSTANCE;
    }

    public TagValidator getTagValidator() {
        return tagValidator;
    }
}
