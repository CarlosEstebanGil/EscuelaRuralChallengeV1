package com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalificacionDTO {
    private Long id;
    private Long tareaId;
    private Long alumnoId;
    private int nota;
}
