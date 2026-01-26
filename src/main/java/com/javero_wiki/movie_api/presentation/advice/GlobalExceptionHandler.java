package com.javero_wiki.movie_api.presentation.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice // Reunimos las excepciones
public class GlobalExceptionHandler {

    // Redirigimos la excepción al método
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
        MethodArgumentNotValidException ex // La excepción se inyecta en el parámetro
    ) {
        Map<String, String> errors = new HashMap<>();
        
        ex.getBindingResult().getFieldErrors()
            .forEach(e -> 
                errors.put(e.getField(), e.getDefaultMessage())
            );

        // Forma completa y antigua pero útil
        // return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

        // Forma simplificada
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEntityNotFoundException(
        EntityNotFoundException ex
    ) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(Map.of("ENTITY_NOT_FOUND", ex.getMessage()));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoResourceFoundException(
        NoResourceFoundException ex
    ) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(Map.of("ENDPOINT_NOT_FOUND", "El recurso no existe"));
    }

    /* NUEVO */

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleHttpMessageNotReadableException(
        HttpMessageNotReadableException ex
    ){
        return ResponseEntity.badRequest()
            .body(Map.of("REQUEST_BODY_MISSING", "Necesitas el cuerpo de la petición"));
    }
    
}
