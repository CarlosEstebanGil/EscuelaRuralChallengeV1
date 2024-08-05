package com.charly.sbSec3Jwt.escuelaRural.fecha;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FechaService {

    //List<Fecha> findAll();
	public List<FechaDTO> findAll(); 

    Optional<Fecha> findById(LocalDateTime fecha);

    Fecha save(Fecha fecha);

    void deleteById(LocalDateTime fecha);
}
