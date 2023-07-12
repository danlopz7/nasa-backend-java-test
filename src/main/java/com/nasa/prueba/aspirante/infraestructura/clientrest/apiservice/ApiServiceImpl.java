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

    @Autowired
    private RestTemplate restTemplate;

    @Value("${nasa.base-url}")
    private String baseUrl;

    @Override
    public String getAPI() throws JsonProcessingException, URISyntaxException {

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
    public List<PruebaEntity> getAPI2(String query) throws JsonProcessingException, URISyntaxException {

        /*MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("q", "apollo 2011");
        String url2 = "https://images-api.nasa.gov/search";
        String encodedUrl = UriComponentsBuilder.fromHttpUrl(url2)
                .queryParams(params)
                .toUriString();
        System.out.println("encoded " + encodedUrl);
        String decoded = URLDecoder.decode(encodedUrl, StandardCharsets.UTF_8);
        System.out.println("decoded " + decoded);
        decoded.replace(" ", "%");
        System.out.println("decoded again " + decoded);*/
        String baseQuery = "?q=" + query;
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

}
