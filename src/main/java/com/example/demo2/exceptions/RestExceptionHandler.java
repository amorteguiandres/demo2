package com.example.demo2.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {


    @ExceptionHandler(BusinessExceptions.class)
    public ResponseEntity<ErrorDTO> handleException(BusinessExceptions ex) {
        log.error("BusinessExceptions: ", ex);
        return new ResponseEntity<>(new ErrorDTO(ex.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Exception.class)
    public  ResponseEntity<ErrorDTO> handleException(Exception ex){
        log.error("ERROR: Exception occurred - > : ", ex);
        return new ResponseEntity<>(new ErrorDTO("Ha ocurrido un error interno."), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
