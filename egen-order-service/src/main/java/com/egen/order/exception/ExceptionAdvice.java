package com.egen.order.exception;

import com.egen.order.model.domain.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(
            Exception exception
    ){
        logError(exception.getClass().getSimpleName(),exception.getMessage());
        return new ResponseEntity<>(new ErrorResponse("Unable to process the request"), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException exception) {

        logError(exception.getClass().getSimpleName(),exception.getMessage());

        List<String> errors = new ArrayList<>();

        exception.getBindingResult().getAllErrors().forEach((error) -> {
            errors.add(error.getDefaultMessage());
        });


        return new ResponseEntity<>(new ErrorResponse(errors),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderServiceException.class)
    public ResponseEntity<ErrorResponse> handleOrderServiceException(OrderServiceException exception){

        logError(exception.getClass().getSimpleName(),exception.getMessage());

        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()),HttpStatus.BAD_REQUEST);

    }

    protected void logError(String exception, String message){
        log.error("{} exception thrown from controller with the message: {}",exception,
                message);
    }
}
