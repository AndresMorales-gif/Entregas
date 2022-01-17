package com.ceiba.usuario.consulta;

import java.util.List;

import com.ceiba.usuario.puerto.dao.DaoUsuarioListar;
import org.springframework.stereotype.Component;

import com.ceiba.usuario.modelo.dto.DtoUsuario;

@Component
public class ManejadorListarUsuarios {

    private final DaoUsuarioListar daoUsuarioListar;

    public ManejadorListarUsuarios(DaoUsuarioListar daoUsuarioListar){
        this.daoUsuarioListar = daoUsuarioListar;
    }

    public List<DtoUsuario> ejecutar(){ return this.daoUsuarioListar.listar(); }
}
