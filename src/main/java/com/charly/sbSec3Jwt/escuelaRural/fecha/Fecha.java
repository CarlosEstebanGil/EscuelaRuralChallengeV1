package com.charly.sbSec3Jwt.escuelaRural.fecha;

import java.time.LocalDateTime;
import java.util.List;

import com.charly.sbSec3Jwt.escuelaRural.asistencia.Asistencia;
import com.charly.sbSec3Jwt.escuelaRural.reporte.Reporte;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
@Table(indexes = {
    @Index(name = "idx_fecha", columnList = "fecha")
})
@ToString(onlyExplicitlyIncluded = true)
public class Fecha {
	 @Id
	    @ToString.Include
	    private LocalDateTime fecha;

	    @Column(nullable = false)
	    @ToString.Include
	    private boolean lluvioso;

	    @Column(nullable = false)
	    @ToString.Include
	    private boolean feriado;

	    @OneToOne(mappedBy = "fechaEntity", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	    @ToString.Exclude
	    private Reporte reporte;
	    
	    @OneToMany(mappedBy = "fecha",fetch = FetchType.EAGER)
	    private List<Asistencia> asistencias;
	    
	    // Constructor sin reporte para evitar la relación cíclica
	    public Fecha(LocalDateTime fecha, boolean lluvioso, boolean feriado) {
	        this.fecha = fecha;
	        this.lluvioso = lluvioso;
	        this.feriado = feriado;
	    }
}
