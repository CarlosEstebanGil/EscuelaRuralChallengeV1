package com.charly.sbSec3Jwt.escuelaRural.preceptor;

import com.charly.sbSec3Jwt.escuelaRural.curso.Curso;
import com.charly.sbSec3Jwt.escuelaRural.miembro.Miembro;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Preceptor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "miembro_id", nullable = false)
	private Miembro miembro;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "curso_id", nullable = false)
	private Curso curso;

	public Preceptor(Miembro miembro, Curso curso) {
		this.miembro = miembro;
		this.curso = curso;
	}

	// aca irian mas campos adicionales que pudieran ser necesarios para preceptores

}
