package com.javero_wiki.movie_api.service.implementation;

import java.util.List;
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
import com.javero_wiki.movie_api.presentation.dto.MovieUpdateDto;
import com.javero_wiki.movie_api.service.interfaces.ICommonService;

@Service
public class MovieService implements ICommonService<MovieDto, MovieInsertDto, MovieUpdateDto> {

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
    @Transactional
    public MovieDto save(MovieInsertDto insertedDto) {
        try {
            MovieEntity movieEntity = MAPPER.map(insertedDto, MovieEntity.class);
            if (movieEntity == null) {
                throw new IllegalArgumentException("Error de mapeado: MovieEntity es null");
            }
            movieEntity = repository.save(movieEntity);
            return MAPPER.map(movieEntity, MovieDto.class);
        
        } catch (UnsupportedOperationException e) {
            throw new UnsupportedOperationException(
                "Error con el objeto: "+ insertedDto+ " -> "+ e.getMessage()
            );
        }
    }

}
