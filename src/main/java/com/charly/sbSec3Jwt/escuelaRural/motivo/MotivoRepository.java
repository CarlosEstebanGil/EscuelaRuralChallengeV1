package com.charly.sbSec3Jwt.escuelaRural.motivo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotivoRepository extends JpaRepository<Motivo, Long> {
	 Optional<Motivo> findByDescripcion(String descripcion);
}
