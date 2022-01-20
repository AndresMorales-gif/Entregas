package com.ceiba.envio.servicio;

import com.ceiba.dominio.excepcion.ExcepcionNoEncontrado;
import com.ceiba.envio.modelo.dto.DtoEnvio;
import com.ceiba.envio.puerto.dao.DaoEnvioPorId;

public class ServicioConsultarEnvioPorId {

    private static final String EL_ENVIO_NO_EXISTE_EN_EL_SISTEMA = "El envio no existe en el sistema";
    private final DaoEnvioPorId daoEnvioPorId;

    public ServicioConsultarEnvioPorId(DaoEnvioPorId daoEnvioPorId) {
        this.daoEnvioPorId = daoEnvioPorId;
    }

    public DtoEnvio ejecutar(Long id) {
        DtoEnvio envio = this.daoEnvioPorId.encontrarPorId(id);
        if (envio == null) {
            throw new ExcepcionNoEncontrado(EL_ENVIO_NO_EXISTE_EN_EL_SISTEMA);
        }
        return envio;
    }
}
