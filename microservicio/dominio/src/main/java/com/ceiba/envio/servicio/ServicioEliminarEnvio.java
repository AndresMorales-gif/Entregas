package com.ceiba.envio.servicio;

import com.ceiba.dominio.excepcion.ExcepcionNoEncontrado;
import com.ceiba.dominio.excepcion.ExcepcionOperacionInvalida;
import com.ceiba.envio.modelo.dto.DtoEnvio;
import com.ceiba.envio.puerto.dao.DaoEnvioPorId;
import com.ceiba.envio.puerto.repositorio.RepositorioEnvio;

import java.time.LocalDateTime;

public class ServicioEliminarEnvio {

    private static final String ENVIO_NO_ENCONTRADO = "El envio no se encontro en el sistema";
    private static final String ENVIO_EN_PROCESO_OPERACION_INVALIDA = "El envio se encuentra en proceso. No es posible eliminarlo";

    private static final Long DIAS_OPERACION = 3L;

    private RepositorioEnvio repositorioEnvio;
    private DaoEnvioPorId daoEnvioPorId;

    public ServicioEliminarEnvio(RepositorioEnvio repositorioEnvio, DaoEnvioPorId daoEnvioPorId) {
        this.repositorioEnvio = repositorioEnvio;
        this.daoEnvioPorId = daoEnvioPorId;
    }

    public void ejecutar(Long id) {
        DtoEnvio dtoEnvio = encontrarEnvioAnterior(id);
        validarFechaOperacionValida(dtoEnvio);
        this.repositorioEnvio.eliminar(id);
    }

    private DtoEnvio encontrarEnvioAnterior(Long id) {
        DtoEnvio dtoEnvio = this.daoEnvioPorId.encontrarPorId(id);
        if (dtoEnvio == null) {
            throw new ExcepcionNoEncontrado(ENVIO_NO_ENCONTRADO);
        }
        return dtoEnvio;
    }

    private void validarFechaOperacionValida(DtoEnvio dtoEnvio) {
        LocalDateTime fechaMinimaOperacion = LocalDateTime.now().plusDays(DIAS_OPERACION);
        if (dtoEnvio.getFechaEntrega().compareTo(fechaMinimaOperacion) < 0) {
            throw new ExcepcionOperacionInvalida(ENVIO_EN_PROCESO_OPERACION_INVALIDA);
        }
    }
}
