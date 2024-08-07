package com.example.springbootcrud.exceptionhandler;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity hanldeUserNotFoundException(UserNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found With ID"+ e.getMessage());

    }
    @ExceptionHandler(DuplicateUSerException.class)
    public ResponseEntity handlerDuplicateUserException(DuplicateUSerException e){
        return ResponseEntity.status((HttpStatus.NOT_ACCEPTABLE)).body("USer Already Exist in Database");
    }
}
