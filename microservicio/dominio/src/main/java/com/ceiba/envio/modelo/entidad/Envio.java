package com.ceiba.envio.modelo.entidad;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static com.ceiba.dominio.ValidadorArgumento.validarDiferente;
import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
@Setter
public class Envio {

    private static final String SE_DEBE_INGRESAR_EL_REMITENTE = "Se debe ingresar el remitente del envio";
    private static final String SE_DEBE_INGRESAR_EL_DESTINATARIO = "Se debe ingresar el destinatario del envio";
    private static final String SE_DEBE_INGRESAR_LA_ZONA = "Se debe ingresar la zona del envio";
    private static final String SE_DEBE_INGRESAR_EL_PESO_DE_LA_CARGA = "Se debe ingresar el peso de la carga del envio";
    private static final String EL_REMITENTE_DESTINATARIO_IGUAL = "El remitente no puede ser el mismo que el destinatario";

    private Long id;
    private Long remitente;
    private Long destinatario;
    private Long zona;
    private Boolean envioPlus;
    private Long pesoCarga;
    private LocalDateTime fechaEntrega;
    private Double precio;
    private LocalDateTime fechaCreacion;

    public Envio(Long id, Long remitente, Long destinatario, Long zona, Boolean envioPlus, Long pesoCarga) {
        validarObligatorio(remitente, SE_DEBE_INGRESAR_EL_REMITENTE);
        validarObligatorio(destinatario, SE_DEBE_INGRESAR_EL_DESTINATARIO);
        validarObligatorio(zona, SE_DEBE_INGRESAR_LA_ZONA);
        validarObligatorio(pesoCarga, SE_DEBE_INGRESAR_EL_PESO_DE_LA_CARGA);
        validarDiferente(remitente, destinatario, EL_REMITENTE_DESTINATARIO_IGUAL);

        if (envioPlus == null) {
            envioPlus = Boolean.FALSE;
        }

        this.id = id;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.zona = zona;
        this.envioPlus = envioPlus;
        this.pesoCarga = pesoCarga;
        this.fechaCreacion = LocalDateTime.now();
    }
}
