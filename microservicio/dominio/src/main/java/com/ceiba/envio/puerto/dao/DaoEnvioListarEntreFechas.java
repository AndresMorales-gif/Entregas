package com.ceiba.envio.puerto.dao;

import com.ceiba.envio.modelo.dto.DtoEnvio;

import java.time.LocalDateTime;
import java.util.List;

public interface DaoEnvioListarEntreFechas {

    /**
     * Permite listar envios
     * @return los envios entre las fechas especificadas
     */
    List<DtoEnvio> listarEntreFechas(String remitente, LocalDateTime fechaInicio, LocalDateTime fechaFinal);
}
