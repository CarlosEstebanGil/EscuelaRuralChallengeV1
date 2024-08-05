package com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.entities.Calificacion;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {
}
