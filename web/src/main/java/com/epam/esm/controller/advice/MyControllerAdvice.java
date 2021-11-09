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

/**
 * @author Valeria
 * This is MyControllerAdvice. All wrong responses will come here and be processed.
 */
@ControllerAdvice
public class MyControllerAdvice {

    /**
     * Handler ValidatorException here
     * @param exception linc on ValidatorException object
     * @return Exception string with http status code
     */
    @ExceptionHandler(ValidatorException.class)
    public ResponseEntity<String> handleEmptyInput(ValidatorException exception){
        return new ResponseEntity<>("Try to send incorrect data! Please look into it.", HttpStatus.BAD_REQUEST);
    }

    /**
     * Handler NumberFormatException here
     * @param exception linc on NumberFormatException object
     * @return Exception string with http status code
     */
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<String> handleInvalidData(NumberFormatException exception){
        return new ResponseEntity<>("Try to send incorrect data! Please look into it.", HttpStatus.BAD_REQUEST);
    }

    /**
     * Handler ServiceException here
     * @param exception linc on ServiceException object
     * @return Exception string with http status code
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<String> handleInvalidData(ServiceException exception){
        return new ResponseEntity<>("Try to send incorrect data! Please look into it.", HttpStatus.BAD_REQUEST);
    }

    /**
     * Handler DAOException here
     * @param exception linc on DAOException object
     * @return Exception string with http status code
     */
    @ExceptionHandler(DAOException.class)
    public ResponseEntity<String> handleInvalidData(DAOException exception){
        return new ResponseEntity<String>("Some problems!", HttpStatus.valueOf("451"));
    }

    /**
     * Handler NullPointerException here
     * @param exception linc on NullPointerException object
     * @return Exception string with http status code
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleInvalidData(NullPointerException exception){
        return new ResponseEntity<String>("Data not found!", HttpStatus.valueOf("500"));
    }
}
