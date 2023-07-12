package com.nasa.prueba.aspirante.infraestructura.clientrest.apiservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nasa.prueba.aspirante.dominio.entities.PruebaEntity;

import java.net.URISyntaxException;
import java.util.List;

public interface ApiService {

    public String consumeApi() throws JsonProcessingException, URISyntaxException;

    public List<PruebaEntity> consumeApi2() throws JsonProcessingException, URISyntaxException;

    public void setQuery(String q);
}
