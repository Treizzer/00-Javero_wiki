package com.javero_wiki.movie_api.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javero_wiki.movie_api.persistence.entity.MovieEntity;

@Repository
public interface IMovieRepository extends CrudRepository<MovieEntity, Long> {
    
}
