package com.nasa.prueba.aspirante.infraestructura.restcontroller.service;

import com.nasa.prueba.aspirante.dominio.entities.PruebaEntity;

import java.util.List;

public interface IPruebaService {

    public List<PruebaEntity> findAll();

    public void save(PruebaEntity pruebaEntity);

    public PruebaEntity findOne(Long id);

    public void delete(Long id);
}
