package com.nasa.prueba.aspirante.aplicacion.taskscheduler;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/*@Component
public class ScheduledTasks {

    @Autowired
    private TaskScheduler taskScheduler;

    @Autowired
    private PruebaTask2 task2;

    @PostConstruct
    public void scheduleTasks() {
        taskScheduler.schedule(task2, new PeriodicTrigger(2, TimeUnit.SECONDS));
        //taskScheduler.schedule(task2, new CronTrigger("0 0 4 1/1 * ? *"));
    }
}*/
