package com.charly.sbSec3Jwt.escuelaRural.fecha;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FechaDTO {
    private LocalDateTime fecha;
    private boolean lluvioso;
    private boolean feriado;
}
