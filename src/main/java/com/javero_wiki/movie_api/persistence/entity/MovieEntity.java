package com.javero_wiki.movie_api.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movies")
public class MovieEntity {

    // Cada atributo es una Columna
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // Modificar las propiedades de la columna
    private String title;
    
    @Column(name = "release_year", nullable = false)
    private int releaseYear;
    
    private Double budget; // Cambiado

    @Column(nullable = false)
    private int duration;

    private Integer rating; // Cambiado
    
    @Column(nullable = false) // Nuevo
    private String genre;
    
}
