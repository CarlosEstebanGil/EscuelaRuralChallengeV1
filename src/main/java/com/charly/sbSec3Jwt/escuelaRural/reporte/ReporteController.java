package com.charly.sbSec3Jwt.escuelaRural.reporte;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @Autowired
    private ReporteServiceImpl reporteService;

    @Autowired
    private ReporteMapper reporteMapper;

    @PostMapping
    public ResponseEntity<ReporteDTO> crearReporte(@RequestBody ReporteDTO reporteDTO) {
        ReporteDTO nuevoReporteDTO = reporteService.crearReporte(reporteDTO);
        return ResponseEntity.ok(nuevoReporteDTO);
    }

    @GetMapping
    public List<ReporteDTO> obtenerReportes() {
        return reporteService.obtenerTodosLosReportes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReporteDTO> obtenerReportePorId(@PathVariable Long id) {
        Optional<ReporteDTO> reporteDTO = reporteService.obtenerReportePorId(id);
        return reporteDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}