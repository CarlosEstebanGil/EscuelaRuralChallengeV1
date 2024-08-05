package com.charly.sbSec3Jwt.escuelaRural.asistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.charly.sbSec3Jwt.escuelaRural.fecha.Fecha;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
	 List<Asistencia> findByFecha(Fecha fecha);
	 
	List<Asistencia> findByFechaAndPresente(Fecha fecha, boolean presente);

    List<Asistencia> findByFechaAndPresenteFalse(Fecha fecha);
    
	long countByFechaAndPresente(Fecha fecha, boolean presente);
	
	long countByFechaAndPresenteAndJustificacionIsNotNull(Fecha fecha, boolean presente);
	



}
