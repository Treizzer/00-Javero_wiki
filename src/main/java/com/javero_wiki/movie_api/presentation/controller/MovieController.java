package com.javero_wiki.movie_api.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.javero_wiki.movie_api.presentation.dto.MovieDto;
import com.javero_wiki.movie_api.presentation.dto.MovieInsertDto;
import com.javero_wiki.movie_api.presentation.dto.MovieReplaceDto;
import com.javero_wiki.movie_api.presentation.dto.MovieUpdateDto;
import com.javero_wiki.movie_api.service.interfaces.ICommonService;

import jakarta.validation.Valid;

@RestController // Nivel de importancia similar al @Service y @Repository
@RequestMapping("/api/v1/movies") // Ruta base (evitamos repetir)
public class MovieController {

    @Autowired
    ICommonService<MovieDto, MovieInsertDto, MovieUpdateDto, MovieReplaceDto> service;

    // Todos tus enpoints deberían de ser públicos
    @GetMapping
    public ResponseEntity<List<MovieDto>> findAll() {
        return ResponseEntity.ok(service.findAll()); // Respuesta 200 (Ok)
    }

    @GetMapping("/{id}") // Agregamos nueva ruta de punto de acceso
    public ResponseEntity<MovieDto> findById(@PathVariable long id) {
        // Verificamos que sea número del 1 hacia adelante
        if (id <= 0) {
            return ResponseEntity.badRequest().build(); // Respuesta de Error
        }

        // Respuesta Correcta
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<MovieDto> save(@Valid @RequestBody MovieInsertDto insertedDto) {
        MovieDto movieDto = service.save(insertedDto);

        // Respuesta 201 (creación)
        return ResponseEntity
            .created(ServletUriComponentsBuilder.fromCurrentRequest() // Creamos un URI
                .path("/{id}") // Retornamos en los "headers" una ruta
                .buildAndExpand(movieDto.getId()) // Valor de la ruta "{id}"
                .toUri()) // Transformamos
            .body(movieDto); // Retornamos el movieDto
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<MovieDto> updatePartialById(@Valid @RequestBody MovieUpdateDto updatedDto, 
        @PathVariable long id) {
        if (id <= 0) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(service.updatePartialById(updatedDto, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> replaceById(@Valid @RequestBody MovieReplaceDto updatedDto,
        @PathVariable long id) {
        if (id <= 0) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(service.replaceById(updatedDto, id));
    }

    /* NUEVO */

    @DeleteMapping("/{id}")
    public ResponseEntity<MovieDto> deleteById(@PathVariable long id) {
        if (id <= 0) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(service.deleteById(id));
    }
    
}
