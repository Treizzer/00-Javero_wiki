package com.javero_wiki.movie_api.presentation.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // Reunimos las excepciones
public class GlobalExceptionHandler {

    // Redirigimos la excepción al método
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
        MethodArgumentNotValidException ex // La excepción se inyecta en el parámetro
    ) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
            .forEach(e -> errors.put(
                e.getField(), 
                e.getDefaultMessage()+ " -> "+ e.getRejectedValue())
            );

        // Forma completa y antigua pero útil
        // return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

        // Forma simplificada
        return ResponseEntity.badRequest().body(errors);
    }
    
}
