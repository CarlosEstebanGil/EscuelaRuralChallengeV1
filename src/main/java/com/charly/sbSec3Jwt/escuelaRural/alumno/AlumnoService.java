package com.charly.sbSec3Jwt.escuelaRural.alumno;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.dtos.TareaDTO;
import com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.entities.Nota;
import com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.entities.Tarea;

public interface AlumnoService {

    
    public List<TareaDTO> verTareas();

    public TareaDTO enviarTarea(TareaDTO tareaDTO);

    public List<Nota> verNotas();

	/*@Override
	 public List<Alumno> findAll() {
	      return alumnoRepository.findAll();
	 }*/
	List<Alumno> getAllAlumnos();

	List<Alumno> getAllAlumnosWithDetails();

	Optional<Alumno> findById(Long id);

	Alumno save(Alumno alumno);

	void deleteById(Long id);

	Alumno findAlumnoById(Long id);
	
	
}