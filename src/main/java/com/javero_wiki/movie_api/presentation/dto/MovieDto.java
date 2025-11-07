package com.javero_wiki.movie_api.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// @FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieDto {

    private Long id;
    private String title;
    private int releaseYear;
    private double budget;
    private int duration;
    private float rating;
    private String genre;
    
}
