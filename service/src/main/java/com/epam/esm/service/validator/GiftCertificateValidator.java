package com.epam.esm.service.validator;

import com.epam.esm.dto.GiftCertificateDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class GiftCertificateValidator {
    private static final String NAME_REGEX_PATTERN = "[a-zA-Z ]* {3,}";
    private static final String PART_OF_NAME_REGEX_PATTERN = "[a-zA-Z]{1,}";
    private static final String DATE_REGEX_PATTERN = "(\\d{4})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])";
    private static final String DOUBLE_REGEX_PATTERN = "[0-9]+(\\.){0,1}[0-9]*";
    private static final String INTEGER_REGEX_PATTERN = "\\d+";
    private static final int MAX_DESCRIPTION_FIELD_LENGTH = 100;

    public void validateNewCertificate(GiftCertificateDTO giftCertificate) throws ValidatorException {
        if (giftCertificate == null) {
            throw new ValidatorException("service.validationCommonError");
        }
        validateName(giftCertificate.getName());
        validateDescription(giftCertificate.getDescription());
        validateDuration(giftCertificate.getDuration());
        validatePrice(giftCertificate.getPrice());
        validateDates(giftCertificate.getCreateDate(), giftCertificate.getLastUpdateDate());
    }

    public void validateUpdateData(HashMap updateParams) throws ValidatorException {
        if (updateParams.isEmpty()) {
            throw new ValidatorException("No such params for validation");
        }

        for(Object key: updateParams.keySet()){
            switch (key.toString()){
                case ("name"):
                    validateName(updateParams.get(key).toString());
                    break;
                case ("id"):
                    validateId(Integer.parseInt(updateParams.get(key).toString()));
                    break;
                case ("description"):
                    validateDescription(updateParams.get(key).toString());
                    break;
                case ("price"):
                    validatePrice(Double.parseDouble(updateParams.get(key).toString()));
                    break;
                case ("duration"):
                    validateDuration(Integer.parseInt(updateParams.get(key).toString()));
                    break;
                case ("create_date"):
                case ("last_update_date"):
                    validateDates(updateParams.get(key).toString());
                    break;
            }
        }
    }

    public void validateName(String name) throws ValidatorException{
        if(StringUtils.isBlank(name) || !name.matches(NAME_REGEX_PATTERN)){
            throw new ValidatorException("Certificate name failed validation");
        }
    }

    public void validatePartOfName(String part) throws ValidatorException{
        if(StringUtils.isBlank(part) || !part.matches(PART_OF_NAME_REGEX_PATTERN)){
            throw new ValidatorException("Certificate part of name failed validation");
        }
    }

    private void validateDescription(String description) throws ValidatorException {
        if (StringUtils.isBlank(description) || description.length() > MAX_DESCRIPTION_FIELD_LENGTH) {
            throw new ValidatorException("Certificate description failed validation");
        }
    }

    public void validateDates(String... dates) throws ValidatorException{
        for(String date: dates) {
            if (StringUtils.isBlank(date) || !date.matches(DATE_REGEX_PATTERN)) {
                throw new ValidatorException("Certificate date failed validation");
            }
        }
    }

    public void validateDuration(Integer duration) throws ValidatorException{
        if(duration == null || !duration.toString().matches(INTEGER_REGEX_PATTERN)){
            throw new ValidatorException("Certificate duration failed validation");
        }
    }

    public void validateId(Integer id) throws ValidatorException{
        if(id == null || !id.toString().matches(INTEGER_REGEX_PATTERN)){
            throw new ValidatorException("Certificate id failed validation");
        }
    }

    public void validatePrice(Double price) throws ValidatorException{
        if(price == null || !price.toString().matches(DOUBLE_REGEX_PATTERN)){
            throw new ValidatorException("Certificate price failed validation");
        }
    }


}
