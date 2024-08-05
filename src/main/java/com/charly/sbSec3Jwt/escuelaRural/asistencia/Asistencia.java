package com.charly.sbSec3Jwt.escuelaRural.asistencia;

import com.charly.sbSec3Jwt.escuelaRural.alumno.Alumno;
import com.charly.sbSec3Jwt.escuelaRural.fecha.Fecha;
import com.charly.sbSec3Jwt.escuelaRural.justificacion.Justificacion;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Asistencia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)//LAZY)
	@JoinColumn(name = "alumno_id", nullable = false)
	@JsonManagedReference
	private Alumno alumno;

	@ManyToOne(fetch = FetchType.EAGER)//LAZY)
	@JoinColumn(name = "fecha", nullable = false)
	private Fecha fecha;

	@Column(nullable = false)
	private boolean presente;

	@ManyToOne(fetch = FetchType.EAGER)//LAZY)
	@JoinColumn(name = "justificacion_id", nullable = true)
	private Justificacion justificacion;
}
