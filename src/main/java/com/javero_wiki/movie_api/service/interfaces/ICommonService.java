package com.javero_wiki.movie_api.service.interfaces;

import java.util.List;

// T = Generic Object
// TI = Generic Insert Object
// TU = Generic Update Object
public interface ICommonService<T, TI, TU> {

    List<T> findAll();

    T save(TI insertedDto);
    
}
