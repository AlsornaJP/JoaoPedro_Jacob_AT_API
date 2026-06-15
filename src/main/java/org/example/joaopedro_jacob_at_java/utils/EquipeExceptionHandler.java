package org.example.joaopedro_jacob_at_java.utils;

import org.example.joaopedro_jacob_at_java.exception.NomeAlreadyExistsException;
import org.example.joaopedro_jacob_at_java.exception.SiglaAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EquipeExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> NomeExisteHandler(NomeAlreadyExistsException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> SiglaExisteHandler(SiglaAlreadyExistsException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }
}
