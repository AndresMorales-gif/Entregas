package com.ceiba.envio.servicio.testdatabuilder;

import com.ceiba.envio.modelo.entidad.Envio;

public class EnvioTestDataBuilder {

    private Long id;
    private String remitente;
    private String destinatario;
    private Long zona;
    private Boolean envioPlus;
    private Long pesoCarga;

    public EnvioTestDataBuilder() {
        this.remitente = "123456";
        this.destinatario = "654321";
        this.zona = 1L;
        this.envioPlus = Boolean.FALSE;
        this.pesoCarga = 15L;
    }

    public EnvioTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public EnvioTestDataBuilder conRemitente(String remitente) {
        this.remitente = remitente;
        return this;
    }

    public EnvioTestDataBuilder conDestinatario(String destinatario) {
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
