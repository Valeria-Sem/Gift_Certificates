package com.epam.esm.controller.advice;

import com.epam.esm.service.ServiceException;
import com.epam.esm.service.validator.ValidatorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(ValidatorException.class)
    public ResponseEntity<String> handleEmptyInput(ValidatorException validatorException){
        return new ResponseEntity<>("Input field is Empty! Please look into it.", HttpStatus.BAD_REQUEST);
    }
}
