package com.ceiba.usuario.consulta;

import com.ceiba.usuario.modelo.dto.DtoUsuario;
import com.ceiba.usuario.puerto.dao.DaoUsuarioPorDocumento;
import org.springframework.stereotype.Component;

@Component
public class ManejadorUsuarioPorDocumento {

    private final DaoUsuarioPorDocumento daoUsuarioPorDocumento;

    public ManejadorUsuarioPorDocumento(DaoUsuarioPorDocumento daoUsuarioPorDocumento) {
        this.daoUsuarioPorDocumento = daoUsuarioPorDocumento;
    }

    public DtoUsuario ejecutar(String idDocumento) {
        return daoUsuarioPorDocumento.encontrarPorDocumento(idDocumento);
    }
}
