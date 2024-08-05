package com.charly.sbSec3Jwt.escuelaRural.motivo;

import java.util.List;
import java.util.Optional;

public interface MotivoService {

	List<Motivo> findAll();

	Optional<Motivo> findById(Long id);

	Motivo save(Motivo motivo);

	void deleteById(Long id);

}