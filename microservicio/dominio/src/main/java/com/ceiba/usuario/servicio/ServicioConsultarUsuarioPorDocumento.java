package com.ceiba.usuario.servicio;

import com.ceiba.dominio.excepcion.ExcepcionNoEncontrado;
import com.ceiba.usuario.modelo.dto.DtoUsuario;
import com.ceiba.usuario.puerto.dao.DaoUsuarioPorDocumento;

public class ServicioConsultarUsuarioPorDocumento {

    private static final String EL_USUARIO_NO_EXISTE_EN_EL_SISTEMA = "El usuario no existe en el sistema";
    private final DaoUsuarioPorDocumento daoUsuarioPorDocumento;

    public ServicioConsultarUsuarioPorDocumento(DaoUsuarioPorDocumento daoUsuarioPorDocumento) {
        this.daoUsuarioPorDocumento = daoUsuarioPorDocumento;
    }

    public DtoUsuario ejecutar(String idDocumento) {
        DtoUsuario usuario = daoUsuarioPorDocumento.encontrarPorDocumento(idDocumento);
        if (usuario == null) {
            throw new ExcepcionNoEncontrado(EL_USUARIO_NO_EXISTE_EN_EL_SISTEMA);
        }
        return usuario;
    }

}
