package com.charly.sbSec3Jwt.escuelaRural.miembro;

import com.charly.sbSec3Jwt.escuelaRural.nacionalidad.Nacionalidad;
import com.charly.sbSec3Jwt.escuelaRural.tipoUsuario.TipoUsuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Miembro {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

	 @Column(nullable = false, length = 100)
	 private String nombre;

	 @Column(nullable = false, length = 100)
	 private String apellido;

	 @Column(nullable = false, unique = true, length = 20)
	 private String dni;

	 @Column(nullable = false, length = 255)
	 private String password;

	 @ManyToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name = "nacionalidad_id", nullable = false)
	 private Nacionalidad nacionalidad;

	 @ManyToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name = "tipo_usuario_id", nullable = false)
	 private TipoUsuario tipoUsuario;

	 @Column(nullable = false, unique = true, length = 255)
	 private String email;

}
