package com.nasa.prueba.aspirante.aplicacion.taskscheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nasa.prueba.aspirante.dominio.entities.PruebaEntity;
import com.nasa.prueba.aspirante.infraestructura.clientrest.apiservice.ApiService;
import com.nasa.prueba.aspirante.infraestructura.restcontroller.service.PruebaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.util.List;

@Component
public class PruebaTask implements Runnable {
    //private static final Logger log = LoggerFactory.getLogger();

    @Autowired
    private ApiService service;

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    @Autowired
    PruebaServiceImpl pruebaService;
    private int counter = 0;

    @Override
    public void run() {
        List<PruebaEntity> parsedList = null;
        try {
            parsedList = service.consumeApi2();
        } catch (JsonProcessingException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        if (counter < parsedList.size()) {
            pruebaService.save(parsedList.get(counter));
            System.out.println(parsedList.get(counter).toString());
            counter++;
        } else {
            taskScheduler.shutdown();
        }
    }
}
