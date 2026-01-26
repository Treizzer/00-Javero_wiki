package com.javero_wiki.movie_api.service.interfaces;

import java.util.List;

// T = Generic Object
// TI = Generic Insert Object
// TU = Generic Update Object
public interface ICommonService<T, TI, TU, TR> {

    List<T> findAll();

    T findById(long id);

    T save(TI insertedDto);

    T updatePartialById(TU updatedDto, long id);
    
    T replaceById(TR updatedDto, long id);

    T deleteById(long id); // NUEVO
    
}
