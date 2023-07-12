package com.nasa.prueba.aspirante.infraestructura.clientrest.apiservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nasa.prueba.aspirante.dominio.dto.NasaApiResponse;
import com.nasa.prueba.aspirante.dominio.entities.PruebaEntity;

import java.net.URISyntaxException;
import java.util.List;

public interface ApiService {

    public String getAPI() throws JsonProcessingException, URISyntaxException;

    public List<PruebaEntity> getAPI2(String query) throws JsonProcessingException, URISyntaxException;
}
