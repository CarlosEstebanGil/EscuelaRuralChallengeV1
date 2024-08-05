package com.charly.sbSec3Jwt.escuelaRural.nacionalidad;

import java.util.List;
import java.util.Optional;

public interface NacionalidadService {

	List<Nacionalidad> findAll();

	Optional<Nacionalidad> findById(Long id);

	Nacionalidad save(Nacionalidad nacionalidad);

	void deleteById(Long id);

}