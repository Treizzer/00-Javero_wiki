package com.javero_wiki.movie_api.presentation.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class MovieUpdateDto {
    
    @Min(value = 1, message = "El id solo acepta números positivos")
    Long id; // No necesitaremos usar el @NotNull

    @Size(max = 255, message = "El título no debe de exceder los 255 carácteres")
    String title;
    
    @Max(value = 2025, message = "El año debe ser menor o igual a 2025")
    int releaseYear;
    
    @PositiveOrZero(message = "El presupuesto mínimo debe ser un número positivo o cero")
    double budget;
    
    int duration;
    
    @Min(value = 0, message = "La calificación mínima que se puede asignar es 0")
    @Max(value = 100, message = "La calificación máxima que se puede asignar es 100")
    float rating;
    
    @Size(max = 100, message = "El género no debe exceder los 100 carácteres")
    String genre;

}
