package com.ceiba.envio.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DtoEnvio {
    private Long id;
    private String remitente;
    private String destinatario;
    private Long zona;
    private Boolean envioPlus;
    private Long pesoCarga;
    private LocalDateTime fechaEntrega;
    private Double precio;
    private LocalDateTime fechaCreacion;
}
