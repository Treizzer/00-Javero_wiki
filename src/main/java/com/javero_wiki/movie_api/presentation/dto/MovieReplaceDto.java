package com.javero_wiki.movie_api.presentation.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class MovieReplaceDto {

    @NotBlank(message = "Ingresa el título de la película")
    @Size(max = 255, message = "El título no debe de exceder los 255 carácteres")
    String title;

    @NotNull(message = "Ingresa el año de estreno")
    @Min(value = 1900, message = "El año debe ser mayor o igual a 1900")
    @Max(value = 2025, message = "El año debe ser menor o igual a 2025")
    Integer releaseYear;

    @PositiveOrZero(message = "El presupuesto mínimo debe ser un número positivo o cero")
    Double budget;

    @NotNull(message = "Ingrese la duración de la pelicula (en minutos)")
    @Min(value = 30, message = "La duración debe ser como mínimo 30 minutos")
    Integer duration;
    
    @Min(value = 0, message = "La calificación mínima que se puede asignar es 0")
    @Max(value = 100, message = "La calificación máxima que se puede asignar es 100")
    Integer rating;

    @NotBlank(message = "El genero no debe estar en blanco o con solo espacios")
    @Size(max = 100, message = "El género no debe exceder los 100 carácteres")
    String genre;

}
