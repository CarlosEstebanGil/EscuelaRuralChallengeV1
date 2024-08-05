package com.charly.sbSec3Jwt.escuelaRural.tipoUsuario;

import java.util.List;
import java.util.Optional;

public interface TipoUsuarioService {

	List<TipoUsuario> findAll();

	Optional<TipoUsuario> findById(Long id);

	TipoUsuario save(TipoUsuario tipoUsuario);

	void deleteById(Long id);

}