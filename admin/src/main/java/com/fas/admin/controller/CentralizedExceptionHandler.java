package com.fas.admin.controller;

import com.fas.admin.AdminApplication;
import com.fas.admin.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * Central Controller class exception to be thrown to REST end points
 *
 * @author singh
 */
@RestControllerAdvice
@Component
public class CentralizedExceptionHandler {

    Logger logger = LoggerFactory.getLogger(AdminApplication.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public String handleBedNotFound(UserNotFoundException e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AdminNotLoggedInException.class)
    public String handleAdminNotLoggedInException(AdminNotLoggedInException e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidPasswordException.class, InvalidUserTypeException.class, ConstraintViolationException.class,
            MethodArgumentNotValidException.class, UsernameAlreadyExistsException.class})
    public String handleInvalid(Exception e) {
        logger.info(e.getMessage());
        return e.getMessage();
    }
}
