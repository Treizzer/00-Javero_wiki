package com.javero_wiki.movie_api.presentation.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class MovieInsertDto {
    
    @NotBlank(message = "Ingresa el título de la película")
    @Size(max = 255, message = "El título no debe de exceder los 255 carácteres")
    String title;

    @Min(value = 1900, message = "El año debe ser mayor o igual a 1900")
    @Max(value = 2025, message = "El año debe ser menor o igual a 2025")
    int releaseYear;

    @PositiveOrZero(message = "El presupuesto mínimo debe ser un número positivo o cero")
    double budget;

    @Min(value = 30, message = "La duración debe ser como mínimo 30 minutos")
    int duration;
    
    @Min(value = 0, message = "La calificación mínima que se puede asignar es 0")
    @Max(value = 100, message = "La calificación máxima que se puede asignar es 100")
    float rating;

    @Size(max = 100, message = "El género no debe exceder los 100 carácteres")
    String genre;

}

/*
 * @NotNull: El campo no debe ser null.
 * 
 * @NotEmpty: El campo no debe ser null y su tamaño debe ser mayor que cero.
 * 
 * @NotBlank: El campo no debe ser null y debe contener al menos un carácter que
 * no sea un espacio en blanco.
 * 
 * @Size: Controla el tamaño de cadenas, listas, arreglos, etc.
 * 
 * @Min y @Max: Define los valores mínimo y máximo para números.
 * 
 * @Email: Verifica si el campo tiene un formato de correo electrónico válido.
 * 
 * @Pattern: Define una expresión regular que el campo debe cumplir.
 * 
 * @Digits: Define el limite de dígitos que puedes ingresar en números enteros y
 * decimales.
 * 
 * @CreditCardNumber: Valida números de tarjetas de crédito
 * 
 * @Past: Solo acepta fechas pasadas del día actual (fechas).
 * 
 * @Future Solo acepta fechas futuras del día actual (fechas).
 * 
 * @AssertTrue: Solo acepta que el valor sea un true (Booleans).
 * 
 * @AssertFalse: Solo acepta que el valor sea un false (Booleans).
*/
