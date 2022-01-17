package com.ceiba.usuario.puerto.dao;

import com.ceiba.usuario.modelo.dto.DtoUsuario;

import java.util.List;

public interface DaoUsuarioListar {

    /**
     * Permite listar usuarios
     * @return los usuarios
     */
    List<DtoUsuario> listar();
}
