package com.epam.esm.service.validator;

import org.apache.commons.lang3.StringUtils;

public class TagValidator {
    private static final String TAG_REGEX = "[a-zA-Z]{3,}";

    public void validateTagName(String name) throws ValidatorException{
        if(StringUtils.isBlank(name) || !name.matches(TAG_REGEX)){
            throw new ValidatorException("Tag name failed validation");
        }
    }
}
