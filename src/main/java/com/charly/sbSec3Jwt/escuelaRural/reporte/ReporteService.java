package com.charly.sbSec3Jwt.escuelaRural.reporte;

import java.util.List;
import java.util.Optional;

public interface ReporteService {
    // Casos de uso methods
    ReporteDTO crearReporte(ReporteDTO reporteDTO);
    List<ReporteDTO> obtenerTodosLosReportes();
    Optional<ReporteDTO> obtenerReportePorId(Long id);

    // CRUD methods
    List<ReporteDTO> findAll();
    Optional<Reporte> findById(Long id);
    Reporte save(Reporte reporte);
    void deleteById(Long id);
}