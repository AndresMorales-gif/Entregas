package com.ceiba.usuario.servicio.testdatabuilder;

import com.ceiba.usuario.comando.ComandoUsuario;

import java.time.LocalDateTime;
import java.util.UUID;

public class ComandoUsuarioTestDataBuilder {

    private Long id;
    private String nombre;
    private String idDocumento;

    public ComandoUsuarioTestDataBuilder() {
        nombre = UUID.randomUUID().toString();
        idDocumento = "123465";
    }

    public ComandoUsuarioTestDataBuilder conIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
        return this;
    }

    public ComandoUsuario build() {
        return new ComandoUsuario(id,nombre, idDocumento);
    }
}
