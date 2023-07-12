package com.nasa.prueba.aspirante.dominio.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class NasaCollection {

    @JsonProperty("items")
    private NasaItem[] items;

}
