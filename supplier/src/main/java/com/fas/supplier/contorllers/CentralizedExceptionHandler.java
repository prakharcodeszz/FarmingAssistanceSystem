package com.fas.supplier.contorllers;

import com.fas.supplier.SupplierApplication;
import com.fas.supplier.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.ConstraintViolationException;
import java.net.UnknownHostException;

/**
 * Central Controller class exception to be thrown to REST end points
 *
 * @author singh
 */
@RestControllerAdvice
@Component
public class CentralizedExceptionHandler {

    Logger logger = LoggerFactory.getLogger(SupplierApplication.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FarmerNotFoundException.class)
    public String handleFarmerNotFoundException(FarmerNotFoundException e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(SupplierNotFoundException.class)
    public String handleBuyRequestNotFoundException(SupplierNotFoundException e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BuyRequestIdNotFoundException.class)
    public String handleBuyRequestIdNotFoundException(BuyRequestIdNotFoundException e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public String handleProductNotFoundException(ProductNotFoundException e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidUserTypeException.class)
    public String handleInvalidUserTypeException(InvalidUserTypeException e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SupplierLoggedOutException.class)
    public String handleSupplierLoggedOutException(SupplierLoggedOutException e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ConstraintViolationException.class, MethodArgumentNotValidException.class, UnknownHostException.class, HttpClientErrorException.class})
    public String handleInvalid(Exception e) {
        return e.getMessage();
    }
}
