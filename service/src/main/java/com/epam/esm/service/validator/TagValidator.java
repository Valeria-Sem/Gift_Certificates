package com.epam.esm.service.validator;

import com.epam.esm.dto.GiftTagDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class TagValidator {
    private static final String TAG_REGEX = "[a-zA-Z]{3,}";
    private static final String INTEGER_REGEX_PATTERN = "\\d+";

    public void validateGiftTag(GiftTagDTO giftTagDTO) throws ValidatorException {
        if(giftTagDTO == null){
            throw new ValidatorException("service.validationCommonError");
        }

        validateTagName(giftTagDTO.getTagName());
        validateId(giftTagDTO.getGiftId());

    }

    public void validateTagName(String name) throws ValidatorException{
        if(StringUtils.isBlank(name) || !name.matches(TAG_REGEX)){
            throw new ValidatorException("Tag name failed validation");
        }
    }

    public void validateId(Integer id) throws ValidatorException{
        if(id == null || !id.toString().matches(INTEGER_REGEX_PATTERN)){
            throw new ValidatorException("Id failed validation");
        }
    }
}
