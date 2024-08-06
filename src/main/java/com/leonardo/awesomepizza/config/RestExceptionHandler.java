package com.leonardo.awesomepizza.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.leonardo.awesomepizza.exception.CheckException;
import com.leonardo.awesomepizza.exception.DatoNotFoundException;

@RestControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(DatoNotFoundException.class)
    public ResponseEntity<String> handleDatoNotFoundException(DatoNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(CheckException.class)
    public ResponseEntity<String> handleCheckException(CheckException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
	
	
}
