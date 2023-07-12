package com.nasa.prueba.aspirante.infraestructura.clientrest.apiservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nasa.prueba.aspirante.dominio.dto.NasaApiResponse;
import com.nasa.prueba.aspirante.dominio.dto.NasaItem;
import com.nasa.prueba.aspirante.dominio.entities.PruebaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

    /**
     *  Servicio que consume la API
     */

    @Autowired
    private RestTemplate restTemplate;

    @Value("${nasa.base-url}")
    private String baseUrl;

    private String query2;

    public ApiServiceImpl() {
        this.query2 = null;
    }

    @Override
    public String consumeApi() throws JsonProcessingException, URISyntaxException {

        String query = "?q=apollo 2011";
        String url = baseUrl + query.replace(" ", "%");
        System.out.println(url);

        URI uri = new URI(url);
        String jsonResponse = restTemplate.getForObject(uri, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        NasaApiResponse response = objectMapper.readValue(jsonResponse, NasaApiResponse.class);
        NasaItem[] items = response.getCollection().getItems();
        List<PruebaEntity> parsedList = new ArrayList<>();

        for (NasaItem item : items) {
            String href = item.getHref();
            String center = item.getData()[0].getCenter();
            String title = item.getData()[0].getTitle();
            String nasaId = item.getData()[0].getNasaId();

            PruebaEntity p = new PruebaEntity();
            p.setHref(href);
            p.setCenter(center);
            p.setTitle(title);
            p.setNasaId(nasaId);
            parsedList.add(p);
        }
        return jsonResponse;
    }

    @Override
    public List<PruebaEntity> consumeApi2() throws JsonProcessingException, URISyntaxException {
        String baseQuery = "?q=" + this.query2;
        String url = baseUrl + baseQuery.replace(" ", "%");
        System.out.println(url);

        URI uri = new URI(url);
        String jsonResponse = restTemplate.getForObject(uri, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        NasaApiResponse response = objectMapper.readValue(jsonResponse, NasaApiResponse.class);
        NasaItem[] items = response.getCollection().getItems();

        List<PruebaEntity> parsedList = new ArrayList<>();

        for (NasaItem item : items) {
            String href = item.getHref();
            String center = item.getData()[0].getCenter();
            String title = item.getData()[0].getTitle();
            String nasaId = item.getData()[0].getNasaId();

            PruebaEntity p = new PruebaEntity();
            p.setHref(href);
            p.setCenter(center);
            p.setTitle(title);
            p.setNasaId(nasaId);
            parsedList.add(p);
        }

        System.out.println(parsedList.size());
        return parsedList;
    }

    @Override
    public void setQuery(String q) {
        this.query2 = q;
    }

}
