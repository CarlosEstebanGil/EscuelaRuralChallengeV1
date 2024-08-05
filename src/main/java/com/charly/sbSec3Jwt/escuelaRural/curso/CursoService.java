package com.charly.sbSec3Jwt.escuelaRural.curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {

	List<Curso> findAll();

	Optional<Curso> findById(Long id);

	Curso save(Curso curso);

	void deleteById(Long id);

}