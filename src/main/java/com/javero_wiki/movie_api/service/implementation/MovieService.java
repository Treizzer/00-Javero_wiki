package com.javero_wiki.movie_api.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javero_wiki.movie_api.persistence.entity.MovieEntity;
import com.javero_wiki.movie_api.persistence.repository.IMovieRepository;
import com.javero_wiki.movie_api.presentation.dto.MovieDto;
import com.javero_wiki.movie_api.presentation.dto.MovieInsertDto;
import com.javero_wiki.movie_api.presentation.dto.MovieReplaceDto;
import com.javero_wiki.movie_api.presentation.dto.MovieUpdateDto;
import com.javero_wiki.movie_api.service.interfaces.ICommonService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MovieService implements ICommonService<MovieDto, MovieInsertDto, MovieUpdateDto, MovieReplaceDto> {

    @Autowired
    private IMovieRepository repository; // Mostrará todos los métodos de comunicación a la BD

    private static final ModelMapper MAPPER = new ModelMapper();

    @Override
    @Transactional(readOnly = true)
    public List<MovieDto> findAll() {
        // Paso a paso ("var" puede recibir cualquier tipo de resultado)
        Iterable<MovieEntity> entities = repository.findAll();

        List<MovieDto> dtos = StreamSupport // Transforma en stream
            .stream(entities.spliterator(), false)
            // Mapeamos cada movieEntity a un objeto de MovieDto
            .map(movieEntity -> MAPPER.map(movieEntity, MovieDto.class))
            .collect(Collectors.toList());

        return dtos;

        // Volverse crazy
        // return StreamSupport.stream(repository.findAll().spliterator(), false)
        //     .map(m -> MAPPER.map(m, MovieDto.class))
        //     .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public MovieDto findById(long id) {
        // Recibimos el resultado
        Optional<MovieEntity> movieOptional = repository.findById(id);

        // Extraemos el objeto (forma sencilla)
        // MovieEntity entity = movieOptional.get();

        // Ambas formas de extraer sirven, pero está es más personalizada
        MovieEntity entity = movieOptional.orElseThrow(() -> new EntityNotFoundException(
            "No se encontró la película con ID: "+ id
        ));

        // Mapeamos de entidad a dto y regresamos
        return MAPPER.map(entity, MovieDto.class);
    }

    @Override
    @Transactional
    public MovieDto save(MovieInsertDto insertedDto) {
        // En caso de que la transacción falle
        try {

            // Mapear de un objeto dto a uno de entidad
            MovieEntity movieEntity = MAPPER.map(insertedDto, MovieEntity.class);

            if (movieEntity == null) { // Comprobar existencia
                throw new IllegalArgumentException("Error de mapeado: MovieEntity es null");
            }

            movieEntity = repository.save(movieEntity); // Guardamos
            return MAPPER.map(movieEntity, MovieDto.class); // Mapeamos de entidad a dto
        
        } catch (UnsupportedOperationException e) {
            throw new UnsupportedOperationException(
                "Error con el objeto: "+ insertedDto+ " -> "+ e.getMessage()
            );
        }
    }
    
    @Override
    @Transactional
    public MovieDto updatePartialById(MovieUpdateDto updatedDto, long id) {
        // Lo mismo que en el método findById, pero en una sola declaración
        MovieEntity entity = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(
                "No se encontró la película con ID: "+ id
            ));

        // Optional.ofNullable(updatedDto.getTitle()).ifPresent(entity::setTitle);
        // Optional.ofNullable(updatedDto.getReleaseYear()).ifPresent((v) -> entity.setReleaseYear(v));
        // Optional.ofNullable(updatedDto.getBudget()).ifPresent((v) -> entity.setBudget(v));
        // Optional.ofNullable(updatedDto.getDuration()).ifPresent((v) -> entity.setDuration(v));
        // Optional.ofNullable(updatedDto.getRating()).ifPresent((v) -> entity.setRating(v));
        // Optional.ofNullable(updatedDto.getGenre()).ifPresent(entity::setGenre);

        applyUpdates(updatedDto, entity);

        // Dos opciones: 
        // 1. Usas de forma explicita el guardar con ".save()"
        // 2. No declarar el ".save()" y dejar que hibernate haga un dirty checking
        // repository.save(entity);

        return MAPPER.map(entity, MovieDto.class);
    }

    private void applyUpdates(MovieUpdateDto dto, MovieEntity entity) {
        if (dto.getTitle() != null) { 
            entity.setTitle(dto.getTitle()); 
        }
        if (dto.getReleaseYear() != null) { 
            entity.setReleaseYear(dto.getReleaseYear()); 
        }
        if (dto.getBudget() != null) {
            entity.setBudget(dto.getBudget());
        }
        if (dto.getDuration() != null) {
            entity.setDuration(dto.getDuration());
        }
        if (dto.getRating() != null) {
            entity.setRating(dto.getRating());
        }
        if (dto.getGenre() != null) {
            entity.setGenre(dto.getGenre());
        }
    }

    @Override
    @Transactional
    public MovieDto replaceById(MovieReplaceDto updatedDto, long id) {
        MovieEntity entity = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(
                "No se encontró la película con ID: "+ id
            ));

        // Reemplazo total
        entity.setTitle(updatedDto.getTitle());
        entity.setReleaseYear(updatedDto.getReleaseYear());
        entity.setBudget(updatedDto.getBudget());
        entity.setDuration(updatedDto.getDuration());
        entity.setRating(updatedDto.getRating());
        entity.setGenre(updatedDto.getGenre());
        
        return MAPPER.map(entity, MovieDto.class);
    }

    /* NUEVO */

    @Override
    @Transactional
    public MovieDto deleteById(long id) {
        MovieDto dto = this.findById(id);

        repository.deleteById(id);

        return dto;
    }

}
