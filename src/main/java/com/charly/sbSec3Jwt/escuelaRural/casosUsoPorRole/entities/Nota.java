package com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long tareaId;
    private Long alumnoId;
    private int nota;

    public Nota(Long tareaId, Long alumnoId, int nota) {
        this.tareaId = tareaId;
        this.alumnoId = alumnoId;
        this.nota = nota;
    }

}