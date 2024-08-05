package com.charly.sbSec3Jwt.escuelaRural.justificacion;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JustificacionServiceImpl implements JustificacionService {
	
    @Autowired
    private JustificacionRepository justificacionRepository;

    @Override
    public List<Justificacion> findAll() {
        return justificacionRepository.findAll();
    }

    @Override
    public Optional<Justificacion> findById(Long id) {
        return justificacionRepository.findById(id);
    }

    @Override
    public Justificacion save(Justificacion justificacion) {
        return justificacionRepository.save(justificacion);
    }

    @Override
    public void deleteById(Long id) {
        justificacionRepository.deleteById(id);
    }
	
}
