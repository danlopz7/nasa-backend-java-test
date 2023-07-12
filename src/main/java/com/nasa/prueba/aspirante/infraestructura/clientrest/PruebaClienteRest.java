package com.nasa.prueba.aspirante.infraestructura.clientrest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nasa.prueba.aspirante.aplicacion.taskscheduler.PruebaTask;
import com.nasa.prueba.aspirante.dominio.entities.PruebaEntity;
import com.nasa.prueba.aspirante.infraestructura.clientrest.apiservice.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/")
public class PruebaClienteRest {

    /**
     *  Controlador que solicita consumir la API
     */

    @Autowired
    private ApiService service;

    @Autowired
    private TaskScheduler taskScheduler;

    @Autowired
    private PruebaTask pruebaTask;

    @GetMapping("api1")
    public ResponseEntity<String> getApi() throws JsonProcessingException, URISyntaxException {
        return ResponseEntity.ok(service.consumeApi());
    }

    @GetMapping("api2/{query}")
    public ResponseEntity<List<PruebaEntity>> getApi2(@PathVariable String query) throws JsonProcessingException, URISyntaxException {
        service.setQuery(query);
        taskScheduler.schedule(pruebaTask, new PeriodicTrigger(1, TimeUnit.SECONDS));
        return ResponseEntity.ok(service.consumeApi2());
    }
}
