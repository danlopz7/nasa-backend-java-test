package com.nasa.prueba.aspirante.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@Configuration
//@EnableJpaAuditing//(auditorAwareRef = "auditorProvider")
//public class PersistenceConfig {
//
//    /**
//     *  Clase que dota a este proyecto de Spring, a traves de la anotacion @EnableJpaAuditing,
//     *  la capacidad que se puedan auditar las entidades. Annotation to enable auditiing in JPA.
//     */
//    @Bean
//    public AuditorAware<String> auditorProvider() {
//        return new AuditorAwareImpl();
//    }
//}
