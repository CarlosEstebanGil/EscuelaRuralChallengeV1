package com.charly.sbSec3Jwt.escuelaRural.reporte;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReporteServiceImpl implements ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;
    
    @Autowired
    ReporteMapper reporteMapper;
    
    //casos uso methods:
    @Override
    public ReporteDTO crearReporte(ReporteDTO reporteDTO) {
        Reporte reporte = reporteMapper.toEntity(reporteDTO);
        reporte = reporteRepository.save(reporte);
        return reporteMapper.toDTO(reporte);
    }

    @Override
    public List<ReporteDTO> obtenerTodosLosReportes() {
        return reporteRepository.findAll().stream()
                .map(reporteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReporteDTO> obtenerReportePorId(Long id) {
        return reporteRepository.findById(id)
                .map(reporteMapper::toDTO);
    }
    
    /*@Override
    public List<Reporte> findAll() {
        return reporteRepository.findAll();
    }*/
    
    //crud methods:
    
    
    
    public List<ReporteDTO> findAll() {
        return reporteRepository.findAll().stream()
                .map(reporteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Reporte> findById(Long id) {
        return reporteRepository.findById(id);
    }

    @Override
    public Reporte save(Reporte reporte) {
        return reporteRepository.save(reporte);
    }
    
    @Override
    public void deleteById(Long id) {
        reporteRepository.deleteById(id);
    }
    
}
