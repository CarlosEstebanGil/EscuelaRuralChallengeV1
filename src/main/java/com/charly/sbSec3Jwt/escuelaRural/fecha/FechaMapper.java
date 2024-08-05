package com.charly.sbSec3Jwt.escuelaRural.fecha;


import org.springframework.stereotype.Component;

@Component
public class FechaMapper {

    public FechaDTO toDTO(Fecha fecha) {
        return new FechaDTO(
                fecha.getFecha(),
                fecha.isLluvioso(),
                fecha.isFeriado()
        );
    }

    public Fecha toEntity(FechaDTO fechaDTO) {
        return new Fecha(
                fechaDTO.getFecha(),
                fechaDTO.isLluvioso(),
                fechaDTO.isFeriado()
        );
    }
}
