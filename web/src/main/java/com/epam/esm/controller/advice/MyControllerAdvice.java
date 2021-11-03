package com.epam.esm.controller.advice;

import com.epam.esm.dao.DAOException;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.validator.ValidatorException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(ValidatorException.class)
    public ResponseEntity<String> handleEmptyInput(ValidatorException validatorException){
        return new ResponseEntity<>("Try to send incorrect data! Please look into it.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<String> handleInvalidData(NumberFormatException validatorException){
        return new ResponseEntity<>("Try to send incorrect data! Please look into it.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<String> handleInvalidData(ServiceException validatorException){
        return new ResponseEntity<>("Try to send incorrect data! Please look into it.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DAOException.class)
    public ResponseEntity<String> handleInvalidData(DAOException validatorException){
        return new ResponseEntity<String>("Some problems!", HttpStatus.valueOf("451"));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleInvalidData(NullPointerException validatorException){
        return new ResponseEntity<String>("Data not found!", HttpStatus.valueOf("500"));
    }
}
