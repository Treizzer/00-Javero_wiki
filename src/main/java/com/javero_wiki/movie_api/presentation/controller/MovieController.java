package com.javero_wiki.movie_api.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.javero_wiki.movie_api.presentation.dto.MovieDto;
import com.javero_wiki.movie_api.presentation.dto.MovieInsertDto;
import com.javero_wiki.movie_api.presentation.dto.MovieUpdateDto;
import com.javero_wiki.movie_api.service.interfaces.ICommonService;

@RestController // Nivel de importancia similar al @Service y @Repository
@RequestMapping("/api/v1/movies") // Ruta base (evitamos repetir)
public class MovieController {

    @Autowired
    ICommonService<MovieDto, MovieInsertDto, MovieUpdateDto> service;

    // Todos tus enpoints deberían de ser publicos
    @GetMapping
    public ResponseEntity<List<MovieDto>> findAll() {
        return ResponseEntity.ok(service.findAll()); // Respuesta 200 (Ok)
    }

    @PostMapping
    public ResponseEntity<MovieDto> save(@RequestBody MovieInsertDto insertedDto) {
        MovieDto movieDto = service.save(insertedDto);

        // Respuesta 201 (creación)
        return ResponseEntity
            .created(ServletUriComponentsBuilder.fromCurrentRequest() // Creamos un URI
                .path("/{id}") // Retornamos en los "headers" una ruta
                .buildAndExpand(movieDto.getId()) // Valor de la ruta "{id}"
                .toUri()) // Transformamos
            .body(movieDto); // Retornamos el movieDto
    }
    
}
