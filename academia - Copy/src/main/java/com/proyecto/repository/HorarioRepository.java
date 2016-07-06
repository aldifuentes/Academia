package com.proyecto.repository;

import com.proyecto.domain.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by fuentald on 30/06/2016.
 */
@RepositoryRestResource
public interface HorarioRepository extends JpaRepository<Horario, Long> {

}