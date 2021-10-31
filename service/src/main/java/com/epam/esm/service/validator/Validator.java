package com.epam.esm.service.validator;

public class Validator {
    private final TagValidator tagValidator;
    private final GiftCertificateValidator giftCertificateValidator;

    private Validator() {
        tagValidator = new TagValidator();
        giftCertificateValidator = new GiftCertificateValidator();
    }

    private static class ValidatorFactorySingletonHolder {
        static final Validator INSTANCE = new Validator();
    }

    public static Validator getInstance() {
        return ValidatorFactorySingletonHolder.INSTANCE;
    }

    public TagValidator getTagValidator() {
        return tagValidator;
    }

    public GiftCertificateValidator getGiftCertificateValidator() {
        return giftCertificateValidator;
    }
}
