package com.nasa.prueba.aspirante.dominio.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;


@Getter @Setter
@MappedSuperclass // Marcada como una superclase mapeada en vez de como una entidad
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {

    @CreatedDate
    @Column(name = "created_dt", updatable = false)
    //@Temporal(TemporalType.DATE)
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    protected Timestamp createdDt;
}
