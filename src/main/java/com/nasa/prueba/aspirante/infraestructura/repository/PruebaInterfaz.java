package com.nasa.prueba.aspirante.infraestructura.repository;

import com.nasa.prueba.aspirante.dominio.entities.PruebaEntity;
import org.springframework.data.repository.CrudRepository;

public interface PruebaInterfaz extends CrudRepository<PruebaEntity, Long> {


}
