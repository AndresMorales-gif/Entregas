package com.ceiba.zona.puerto.dao;

import com.ceiba.zona.modelo.dto.DtoZona;

public interface DaoZonaPorId {

    /**
     * Permite buscar zona por id
     * @return zona
     */
    DtoZona encontrarZonaPorId(Long zona);
}
