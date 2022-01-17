package com.ceiba.usuario.servicio.testdatabuilder;

import com.ceiba.usuario.modelo.entidad.Usuario;

public class UsuarioTestDataBuilder {

    private Long id;
    private String nombreUsuario;
    private String idDocumento;

    public UsuarioTestDataBuilder() {
        nombreUsuario = "1234";
        idDocumento = "123456";
    }

    public UsuarioTestDataBuilder conIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
        return this;
    }

    public UsuarioTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public UsuarioTestDataBuilder conNombre(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
        return this;
    }

    public Usuario build() {
        return new Usuario(id,nombreUsuario, idDocumento);
    }
}
