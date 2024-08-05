package com.charly.sbSec3Jwt.escuelaRural.nacionalidad;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NacionalidadServiceImpl implements NacionalidadService {

    @Autowired
    private NacionalidadRepository nacionalidadRepository;

    @Override
    public List<Nacionalidad> findAll() {
        return nacionalidadRepository.findAll();
    }

    @Override
    public Optional<Nacionalidad> findById(Long id) {
        return nacionalidadRepository.findById(id);
    }

    @Override
    public Nacionalidad save(Nacionalidad nacionalidad) {
        return nacionalidadRepository.save(nacionalidad);
    }

    @Override
    public void deleteById(Long id) {
        nacionalidadRepository.deleteById(id);
    }	
	
}
