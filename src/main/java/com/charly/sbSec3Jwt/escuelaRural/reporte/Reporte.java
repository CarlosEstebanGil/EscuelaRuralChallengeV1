package com.charly.sbSec3Jwt.escuelaRural.reporte;

import java.time.LocalDateTime;

import com.charly.sbSec3Jwt.escuelaRural.fecha.Fecha;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString(onlyExplicitlyIncluded = true)
public class Reporte {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @ToString.Include
	    private Long id;

	    @Column(nullable = false)
	    @ToString.Include
	    private LocalDateTime fecha;

	    @OneToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "fecha_id", nullable = false)
	    @ToString.Exclude
	    private Fecha fechaEntity;

	    @Column(nullable = false)
	    @ToString.Include
	    private long cantidadAlumnosDelCurso;

	    @Column(nullable = false)
	    @ToString.Include
	    private long cantidadAlumnosPresentes;

	    @Column(nullable = false)
	    @ToString.Include
	    private long cantidadAlumnosAusentes;

	    @Column(nullable = false)
	    @ToString.Include
	    private long cantidadAlumnosAusentesJustificados;

	    @Column(nullable = false)
	    @ToString.Include
	    private long cantidadAlumnosAusentesNoJustificados;

	    // Setter adicional para Fecha
	    public void setFecha(Fecha fechaEntity) {
	        this.fechaEntity = fechaEntity;
	        this.fecha = fechaEntity.getFecha();
	    }
}
