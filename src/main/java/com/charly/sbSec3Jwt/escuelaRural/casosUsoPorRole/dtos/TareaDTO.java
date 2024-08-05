package com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TareaDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private String fechaEntrega;
}
