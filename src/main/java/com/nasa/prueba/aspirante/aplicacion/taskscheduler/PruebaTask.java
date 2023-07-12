package com.nasa.prueba.aspirante.aplicacion.taskscheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nasa.prueba.aspirante.dominio.entities.PruebaEntity;
import com.nasa.prueba.aspirante.infraestructura.clientrest.apiservice.ApiService;
import com.nasa.prueba.aspirante.infraestructura.restcontroller.service.PruebaServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.util.List;

/*@Component
public class PruebaTask {

    @Autowired
    private ApiService service;

    @Autowired
    PruebaServiceImpl pruebaService;

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    private int counter = 0;*/
    //Logger log = (Logger) LoggerFactory.getLogger(PruebaTask.class);

  /*  @Scheduled(fixedRate = 100)
    public void scheduleTaskWithFixedRate() throws JsonProcessingException, URISyntaxException {
        List<PruebaEntity> parsedList = service.getAPI2("apollo 2011");
        if (counter < parsedList.size()) {
            pruebaService.save(parsedList.get(counter));
            System.out.println(parsedList.get(counter).toString());
            counter++;
        } else {
            taskScheduler.shutdown();
        }
    }

    @PostConstruct
    public void startTask() throws URISyntaxException, JsonProcessingException {
        // Iniciar manualmente la tarea programada
        scheduleTaskWithFixedRate();
    }*/
//}
