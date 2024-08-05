package com.charly.sbSec3Jwt.escuelaRural.alumno;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
	/*@EntityGraph(attributePaths = {"curso", "miembro"})
	List<Alumno> findAll(); */
  
	@EntityGraph(attributePaths = {"curso", "miembro"})
    @Query("SELECT a FROM Alumno a")
    List<Alumno> findAllWithBasicDetails();
 
    @EntityGraph(value = "Alumno.details", type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT a FROM Alumno a")
    List<Alumno> findAllWithDetails();
}
