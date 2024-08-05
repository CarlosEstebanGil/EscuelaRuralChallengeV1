package com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.dtos;

import com.charly.sbSec3Jwt.escuelaRural.fecha.Fecha;
import com.charly.sbSec3Jwt.escuelaRural.justificacion.Justificacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TomarListaRequestDTO {
    private Long alumnoId;
    private boolean presente;
    private Fecha fecha;
    private Justificacion justificacion;
}
