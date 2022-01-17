package com.ceiba.usuario.puerto.dao;

import com.ceiba.usuario.modelo.dto.DtoUsuario;

public interface DaoUsuarioPorDocumento {

    /**
     * Permite buscar usuario por documento
     * @return usuario
     */
    DtoUsuario encontrarPorDocumento(String idDocumento);
}
