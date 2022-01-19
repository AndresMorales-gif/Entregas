package com.ceiba.envio.puerto.dao;

import com.ceiba.envio.modelo.dto.DtoEnvio;

public interface DaoEnvioPorId {

    /**
     * Permite buscar envio por id
     * @return envio
     */
    DtoEnvio encontrarPorDocumento(Long id);
}
