package com.charly.sbSec3Jwt.escuelaRural.miembro;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MiembroServiceImpl implements MiembroService {

    @Autowired
    private MiembroRepository miembroRepository;

    @Override
    public List<Miembro> findAll() {
        return miembroRepository.findAll();
    }

    @Override
    public Optional<Miembro> findById(Long id) {
        return miembroRepository.findById(id);
    }

    @Override
    public Miembro save(Miembro miembro) {
        return miembroRepository.save(miembro);
    }

    @Override
    public void deleteById(Long id) {
        miembroRepository.deleteById(id);
    }

}
