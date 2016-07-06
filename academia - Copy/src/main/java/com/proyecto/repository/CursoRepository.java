package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.proyecto.domain.Curso;

@RepositoryRestResource
public interface CursoRepository extends JpaRepository<Curso, Long> {
	//public List<Curso> findByNombre(String nombre);
}


