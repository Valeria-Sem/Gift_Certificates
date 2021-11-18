package com.epam.esm.controller.advice;

import com.epam.esm.dao.DAOException;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.validator.ValidatorException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.SecureRandom;
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
        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handler ServiceException here
     * @param exception linc on ServiceException object
     * @return Exception string with http status code
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<String> handleInvalidData(ServiceException exception){
        SecureRandom random = new SecureRandom();
        int num = random.nextInt(100000);
        String formatted = String.format("%05d", num);

        return new ResponseEntity<>(exception.getLocalizedMessage() + "\n ErrorCode: " + formatted,
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Handler DAOException here
     * @param exception linc on DAOException object
     * @return Exception string with http status code
     */
    @ExceptionHandler(DAOException.class)
    public ResponseEntity<String> handleInvalidData(DAOException exception){
        return new ResponseEntity<String>(exception.getLocalizedMessage(), HttpStatus.valueOf("451"));
    }

    /**
     * Handler NullPointerException here
     * @param exception linc on NullPointerException object
     * @return Exception string with http status code
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleInvalidData(NullPointerException exception){
        return new ResponseEntity<String>(exception.getLocalizedMessage(), HttpStatus.valueOf("500"));
    }
}
