package com.charly.sbSec3Jwt.escuelaRural.reporte;

import org.springframework.stereotype.Component;

@Component
public class ReporteMapper {

	public ReporteDTO toDTO(Reporte reporte) {
        return new ReporteDTO(
                reporte.getId(),
                reporte.getFecha(),
                reporte.getFechaEntity(), // Pasar el objeto Fecha completo
                reporte.getCantidadAlumnosDelCurso(),
                reporte.getCantidadAlumnosPresentes(),
                reporte.getCantidadAlumnosAusentes(),
                reporte.getCantidadAlumnosAusentesJustificados(),
                reporte.getCantidadAlumnosAusentesNoJustificados()
        );
    }

    public Reporte toEntity(ReporteDTO reporteDTO) {
        return new Reporte(
                reporteDTO.getId(),
                reporteDTO.getFecha(),
                reporteDTO.getFechaEntity(), // Usar el objeto Fecha completo
                reporteDTO.getCantidadAlumnosDelCurso(),
                reporteDTO.getCantidadAlumnosPresentes(),
                reporteDTO.getCantidadAlumnosAusentes(),
                reporteDTO.getCantidadAlumnosAusentesJustificados(),
                reporteDTO.getCantidadAlumnosAusentesNoJustificados()
        );
    }  
 }
