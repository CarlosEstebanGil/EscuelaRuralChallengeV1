package com.charly.sbSec3Jwt.escuelaRural.alumno;

import java.util.List;
import com.charly.sbSec3Jwt.escuelaRural.asistencia.Asistencia;
import com.charly.sbSec3Jwt.escuelaRural.curso.Curso;
import com.charly.sbSec3Jwt.escuelaRural.miembro.Miembro;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(onlyExplicitlyIncluded = true)
@Entity
@NamedEntityGraph(name = "Alumno.details", 
    attributeNodes = {
        @NamedAttributeNode("miembro"),
        @NamedAttributeNode("curso"),
        @NamedAttributeNode("asistencias")
    }
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)//FetchType.LAZY) // Obs: tuve algunos problemas de exception con algunas relaciones, solucion rapida eager. solucion adecuada: lazy, 
														//			queries hibernate fecth y uso de DTOs 
    @JoinColumn(name = "miembro_id", nullable = false)
    private Miembro miembro;

    @ManyToOne(fetch = FetchType.EAGER) //.LAZY)
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @OneToMany(mappedBy = "alumno", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    @ToString.Exclude
    private List<Asistencia> asistencias; // la excluyo del toString pero la tengo disponible en el entity y la mapeo al dto rta
    
    public Alumno(Miembro miembro, Curso curso) {
        this.miembro = miembro;
        this.curso = curso;
    }
}

