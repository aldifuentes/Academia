package com.proyecto.repository;

import com.proyecto.domain.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by fuentald on 24/06/2016.
 */
@RepositoryRestResource
public interface DocenteRepository extends JpaRepository<Docente, Long> {
        //public List<Curso> findByNombre(String nombre);
/*
@Query("select d.id from Docente d where d.curso.id == ?")
    public Long findDocenteByCursoId(@Param("cid") Long Id);
*/

}

