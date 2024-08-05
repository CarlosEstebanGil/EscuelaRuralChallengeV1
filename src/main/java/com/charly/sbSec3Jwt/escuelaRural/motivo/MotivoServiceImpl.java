package com.charly.sbSec3Jwt.escuelaRural.motivo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MotivoServiceImpl implements MotivoService {

    @Autowired
    private MotivoRepository motivoRepository;

    @Override
    public List<Motivo> findAll() {
        return motivoRepository.findAll();
    }

    @Override
    public Optional<Motivo> findById(Long id) {
        return motivoRepository.findById(id);
    }

    @Override
    public Motivo save(Motivo motivo) {
        return motivoRepository.save(motivo);
    }

    @Override
    public void deleteById(Long id) {
        motivoRepository.deleteById(id);
    }
    
}
