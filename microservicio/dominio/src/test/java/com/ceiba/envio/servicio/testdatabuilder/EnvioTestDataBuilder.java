package com.ceiba.envio.servicio.testdatabuilder;

import com.ceiba.envio.modelo.entidad.Envio;

public class EnvioTestDataBuilder {

    private Long id;
    private Long remitente;
    private Long destinatario;
    private Long zona;
    private Boolean envioPlus;
    private Long pesoCarga;

    public EnvioTestDataBuilder() {
        this.remitente = 1L;
        this.destinatario = 2L;
        this.zona = 1L;
        this.envioPlus = Boolean.FALSE;
        this.pesoCarga = 15L;
    }

    public EnvioTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public EnvioTestDataBuilder conRemitente(Long remitente) {
        this.remitente = remitente;
        return this;
    }

    public EnvioTestDataBuilder conDestinatario(Long destinatario) {
        this.destinatario = destinatario;
        return this;
    }

    public EnvioTestDataBuilder conZona(Long zona) {
        this.zona = zona;
        return this;
    }

    public EnvioTestDataBuilder conEnvioPlus(Boolean envioPlus) {
        this.envioPlus = envioPlus;
        return this;
    }

    public EnvioTestDataBuilder conPesoCarga(Long pesoCarga) {
        this.pesoCarga = pesoCarga;
        return this;
    }

    public Envio build() {
        return new Envio(id,remitente, destinatario, zona, envioPlus, pesoCarga);
    }

}
