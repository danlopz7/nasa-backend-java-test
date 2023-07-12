package com.nasa.prueba.aspirante.dominio.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter
public class NasaApiResponse implements Serializable {

    @JsonProperty("collection")
    private NasaCollection collection;

}
