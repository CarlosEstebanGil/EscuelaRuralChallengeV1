package com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.entities.Nota;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {
}
