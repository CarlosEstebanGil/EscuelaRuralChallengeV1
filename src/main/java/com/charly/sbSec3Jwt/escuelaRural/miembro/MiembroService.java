package com.charly.sbSec3Jwt.escuelaRural.miembro;

import java.util.List;
import java.util.Optional;

public interface MiembroService {

	List<Miembro> findAll();

	Optional<Miembro> findById(Long id);

	Miembro save(Miembro miembro);

	void deleteById(Long id);

}