package com.charly.sbSec3Jwt.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	private String lastName;
	
	@Column(name = "email", nullable = false, unique = true) 
	private String email;									
															
	private String password;								
															
															
	@Enumerated(EnumType.ORDINAL)
	private Role role;
	
	@Override
	public String getPassword() { 
		return password;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { 
		return List.of(new SimpleGrantedAuthority(role.name())); 
	}
	@Override
	public String getUsername() {
		return email; // porque uso el email como el usuario en la aplicacion ( se podria usar firname, lastname, un nombre creado x el propio user etc)
	}
	@Override
	public boolean isAccountNonExpired() {
		return true; // xa simplificar, pero habria que validar si el usuario ya ha expirado , su cuenta caducó etc 
	}
	@Override
	public boolean isAccountNonLocked() {
		return true; //xa simplif, x ej facebook o webs suelen bloquear por un tiempo al user y usan mucho este atrib
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true; //tmb xa simplificar
	}
	@Override
	public boolean isEnabled() {
		return true; // suele pasar q se ponga en false al ppio xq el user se registra pero luego debe habilitarse a travez del link q  
	}					// le enviamos en su mail para esto , que contiene una url y el token de verif registracion (luego le daremos otro en  
						// el login) y ese si es el que se usará hasta que caduque o se requiera una actualizacion o renovacion. 
	
}
