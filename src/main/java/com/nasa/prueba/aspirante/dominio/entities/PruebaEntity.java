package com.nasa.prueba.aspirante.dominio.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "records")
public class PruebaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "href")
    private String href;

    @Column(name = "center")
    private String center;

    @Column(name = "title")
    private String title;

    @Column(name = "nasa_id")
    private String nasaId;

    //@CreatedDate
    @Column(name = "created_dt", updatable = false)
    //@Temporal(TemporalType.DATE)
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdDt;

    @PrePersist
    public void prePersist() {
        createdDt = LocalDateTime.now();
    }
}
