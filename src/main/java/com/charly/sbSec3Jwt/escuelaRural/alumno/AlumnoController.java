package com.charly.sbSec3Jwt.escuelaRural.alumno;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/alumnos")
public class AlumnoController {
		
		@Autowired
	    private AlumnoService alumnoService;

		@Autowired
		AlumnoMapper alumnoMapper;
		
	    @GetMapping
	    public List<Alumno> getAllAlumnos() {
	        return alumnoService.getAllAlumnos(); //findAll();
	    }
	    

	    @GetMapping("/detalles")
	    public List<AlumnoDTO> getAllAlumnosWithDetails() {
	        return alumnoService.getAllAlumnosWithDetails().stream().map(alumnoMapper::toAlumnoDTO).collect(Collectors.toList());
	    }
	    
	    @GetMapping("/{id}")
	    public ResponseEntity<Alumno> getAlumnoById(@PathVariable Long id) {
	        Optional<Alumno> alumno = alumnoService.findById(id);
	        return alumno.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	    }

	    @PostMapping
	    public Alumno createAlumno(@RequestBody Alumno alumno) {
	        return alumnoService.save(alumno);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Alumno> updateAlumno(@PathVariable Long id, @RequestBody Alumno alumnoDetails) {
	        Optional<Alumno> alumno = alumnoService.findById(id);
	        if (alumno.isPresent()) {
	            Alumno updatedAlumno = alumno.get();
	            updatedAlumno.setMiembro(alumnoDetails.getMiembro());
	            updatedAlumno.setCurso(alumnoDetails.getCurso());
	            return ResponseEntity.ok(alumnoService.save(updatedAlumno));
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteAlumno(@PathVariable Long id) {
	        if (alumnoService.findById(id).isPresent()) {
	            alumnoService.deleteById(id);
	            return ResponseEntity.ok().build();
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
		
}
