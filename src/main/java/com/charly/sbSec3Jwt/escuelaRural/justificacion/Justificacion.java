package com.charly.sbSec3Jwt.escuelaRural.justificacion;

import com.charly.sbSec3Jwt.escuelaRural.motivo.Motivo;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Justificacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER) //LAZY)
	@JoinColumn(name = "motivo_id", nullable = false)
	private Motivo motivo;

	@Column(nullable = false, length = 255)
	private String descripcion;
	
	public Justificacion(Motivo motivo, String descripcion) {
		this.motivo = motivo;
		this.descripcion = descripcion;
	}
}
