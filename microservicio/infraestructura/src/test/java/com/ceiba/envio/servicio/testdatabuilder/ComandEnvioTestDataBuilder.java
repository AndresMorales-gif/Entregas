package com.ceiba.envio.servicio.testdatabuilder;

import com.ceiba.envio.comando.ComandoEnvio;

public class ComandEnvioTestDataBuilder {

    private Long id;
    private Long remitente;
    private Long destinatario;
    private Long zona;
    private Boolean envioPlus;
    private Long pesoCarga;

    public ComandEnvioTestDataBuilder() {
        remitente = 1L;
        destinatario = 2L;
        zona = 1L;
        envioPlus = Boolean.FALSE;
        pesoCarga = 15L;
    }

    public ComandEnvioTestDataBuilder conRemitente(Long remitente) {
        this.remitente = remitente;
        return this;
    }

    public ComandEnvioTestDataBuilder conDestinatario(Long destinatario) {
        this.destinatario = destinatario;
        return this;
    }

    public ComandEnvioTestDataBuilder conEnvioPlus(Boolean envioPlus) {
        this.envioPlus = envioPlus;
        return this;
    }

    public ComandEnvioTestDataBuilder conPesoCarga(Long pesoCarga) {
        this.pesoCarga = pesoCarga;
        return this;
    }

    public ComandoEnvio build() {
        return new ComandoEnvio(id, remitente, destinatario, zona, envioPlus, pesoCarga);
    }

}
