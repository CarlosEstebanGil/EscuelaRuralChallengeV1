package com.charly.sbSec3Jwt.escuelaRural.justificacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JustificacionRepository extends JpaRepository<Justificacion, Long> {

}
