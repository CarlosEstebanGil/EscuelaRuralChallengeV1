package com.charly.sbSec3Jwt.escuelaRural.fecha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FechaServiceImpl implements FechaService {
  
    @Autowired
    private FechaRepository fechaRepository;

    /*@Override
    public List<Fecha> findAll() {
        return fechaRepository.findAll();
    }*/
    @Autowired
    FechaMapper fechaMapper;
    
    public List<FechaDTO> findAll() {
        return fechaRepository.findAll().stream()
                .map(fechaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Fecha> findById(LocalDateTime fecha) {
        return fechaRepository.findById(fecha);
    }

    @Override
    public Fecha save(Fecha fecha) {
        return fechaRepository.save(fecha);
    }

    @Override
    public void deleteById(LocalDateTime fecha) {
        fechaRepository.deleteById(fecha);
    }
}
