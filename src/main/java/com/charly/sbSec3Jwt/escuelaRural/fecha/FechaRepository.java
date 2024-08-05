package com.charly.sbSec3Jwt.escuelaRural.fecha;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FechaRepository extends JpaRepository<Fecha, LocalDateTime > { // Long> { 

}
