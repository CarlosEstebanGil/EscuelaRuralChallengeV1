package com.charly.sbSec3Jwt.escuelaRural.alumno;

import java.util.List;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class AlumnoResponse {
    private List<Alumno> alumnos;
    private String message;

    // Constructor, getters, and setters
    public AlumnoResponse(List<Alumno> alumnos, String message) {
        this.alumnos = alumnos;
        this.message = message;
    }
}
