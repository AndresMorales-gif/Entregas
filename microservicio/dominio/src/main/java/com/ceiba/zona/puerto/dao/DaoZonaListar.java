package com.ceiba.zona.puerto.dao;

import com.ceiba.zona.modelo.dto.DtoZona;

import java.util.List;

public interface DaoZonaListar {

    /**
     * Permite listar zonas
     * @return las zonas
     */
    List<DtoZona> listar();
}
