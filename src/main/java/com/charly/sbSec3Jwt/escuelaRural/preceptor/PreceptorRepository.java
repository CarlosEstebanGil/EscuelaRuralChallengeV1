package com.charly.sbSec3Jwt.escuelaRural.preceptor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreceptorRepository extends JpaRepository<Preceptor, Long> {

}
