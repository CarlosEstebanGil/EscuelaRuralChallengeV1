package com.charly.sbSec3Jwt.escuelaRural.miembro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MiembroDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
}
