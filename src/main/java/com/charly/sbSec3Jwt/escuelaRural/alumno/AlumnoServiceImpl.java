package com.charly.sbSec3Jwt.escuelaRural.alumno;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.dtos.TareaDTO;
import com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.entities.Nota;
import com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.entities.Tarea;
import com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.repositories.NotaRepository;
import com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.repositories.TareaRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class AlumnoServiceImpl implements AlumnoService {

	 @Autowired
	 private AlumnoRepository alumnoRepository;

	 //metodos caso uso role alumno:
	    @Autowired
	    private TareaRepository tareaRepository;

	    @Autowired
	    private NotaRepository notaRepository;

	    @Transactional
	    @Override
	    public Alumno findAlumnoById(Long id) {
	        Alumno alumno = alumnoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Alumno not found"));

	        alumno.getMiembro().getNombre();
	        return alumno;
	    }
	    
	    @Override
	    public List<TareaDTO> verTareas() {
	        return tareaRepository.findAll().stream()
	                .map(tarea -> new TareaDTO(tarea.getId(), tarea.getTitulo(), tarea.getDescripcion(), tarea.getFechaEntrega()))
	                .collect(Collectors.toList());
	    }

	    @Override
	    public TareaDTO enviarTarea(TareaDTO tareaDTO) {
	        Tarea tarea = new Tarea(tareaDTO.getTitulo(), tareaDTO.getDescripcion(), tareaDTO.getFechaEntrega());
	        tarea = tareaRepository.save(tarea);
	        return new TareaDTO(tarea.getId(), tarea.getTitulo(), tarea.getDescripcion(), tarea.getFechaEntrega());
	    }

	    @Override
	    public List<Nota> verNotas() {
	        // Lógica para ver las notas del alumno
	        return notaRepository.findAll();
	    }
	    
	 //metodos crud entidad:
	 
	 /*@Override
	 public List<Alumno> findAll() {
	      return alumnoRepository.findAll();
	 }*/
	 	@Override
	    public List<Alumno> getAllAlumnos() {
	        return alumnoRepository.findAllWithBasicDetails();
	 		
	    }
	 	@Override
	    public List<Alumno> getAllAlumnosWithDetails() {
	        return alumnoRepository.findAllWithDetails();
	    }
	
	 
	 @Override
	 public Optional<Alumno> findById(Long id) {
	     return alumnoRepository.findById(id);
	 }
	 
	 @Override
	 public Alumno save(Alumno alumno) {
	     return alumnoRepository.save(alumno);
	 }

	 @Override
	 public void deleteById(Long id) {
	     alumnoRepository.deleteById(id);
	 }
}
