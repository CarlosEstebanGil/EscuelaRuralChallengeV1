package com.charly.sbSec3Jwt.escuelaRural.justificacion;

import java.util.List;
import java.util.Optional;

public interface JustificacionService {

	List<Justificacion> findAll();

	Optional<Justificacion> findById(Long id);

	Justificacion save(Justificacion justificacion);

	void deleteById(Long id);

}