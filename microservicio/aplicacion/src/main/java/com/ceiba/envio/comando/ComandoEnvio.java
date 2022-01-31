package com.ceiba.envio.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoEnvio {

    private Long id;
    private String remitente;
    private String destinatario;
    private Long zona;
    private Boolean envioPlus;
    private Long pesoCarga;
}
