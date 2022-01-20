package com.ceiba.envio.servicio;

import com.ceiba.dominio.excepcion.ExcepcionNoEncontrado;
import com.ceiba.dominio.excepcion.ExcepcionOperacionInvalida;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.envio.modelo.dto.DtoEnvio;
import com.ceiba.envio.modelo.entidad.Envio;
import com.ceiba.envio.puerto.dao.DaoEnvioPorId;
import com.ceiba.envio.puerto.repositorio.RepositorioEnvio;
import com.ceiba.envio.servicio.base.ServicioBaseEnvio;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.zona.modelo.dto.DtoZona;
import com.ceiba.zona.puerto.dao.DaoZonaPorId;

import java.time.LocalDateTime;

public class ServicioActualizarEnvio extends ServicioBaseEnvio {

    private static final String ENVIO_NO_ENCONTRADO = "El envio no se encontro en el sistema";
    private static final String REMITENTE_NO_PUEDE_CAMBIAR = "El remitente no puede ser diferente";
    private static final String ENVIO_EN_PROCESO_OPERACION_INVALIDA = "El envio se encuentra en proceso. No es posible actualizarlo";

    private static final Long DIAS_OPERACION = 3L;

    private final RepositorioEnvio repositorioEnvio;
    private final DaoEnvioPorId daoEnvioPorId;

    public ServicioActualizarEnvio(RepositorioEnvio repositorioEnvio, RepositorioUsuario repositorioUsuario, DaoZonaPorId daoZonaPorId, DaoEnvioPorId daoEnvioPorId) {
        super(repositorioUsuario, daoZonaPorId);
        this.repositorioEnvio = repositorioEnvio;
        this.daoEnvioPorId = daoEnvioPorId;
    }

    public void ejecutar(Envio envio) {
        DtoEnvio dtoEnvio = encontrarEnvioAnterior(envio);
        validarFechaOperacionValida(dtoEnvio);
        validarMismoRemitente(envio, dtoEnvio);
        if (!envio.getDestinatario().equals(dtoEnvio.getDestinatario())) {
            validarExistenciaPreviaUsuario(envio.getDestinatario(), EL_DESTINATARIO);
        }
        if (!envio.getZona().equals(dtoEnvio.getZona())
            || !envio.getEnvioPlus().equals(dtoEnvio.getEnvioPlus())) {
            DtoZona zona = encontrarZona(envio.getZona());
            calcularFechaEntrega(zona, envio);
        }
        calcularPrecio(envio);
        this.repositorioEnvio.actualizar(envio);
    }

    private void validarMismoRemitente(Envio envio, DtoEnvio dtoEnvio) {
        if (envio.getRemitente().compareTo(dtoEnvio.getRemitente()) != COMPARACION_IGUAL) {
            throw new ExcepcionValorInvalido(REMITENTE_NO_PUEDE_CAMBIAR);
        }
    }

    private DtoEnvio encontrarEnvioAnterior(Envio envio) {
        DtoEnvio dtoEnvio = this.daoEnvioPorId.encontrarPorId(envio.getId());
        if (dtoEnvio == null) {
            throw new ExcepcionNoEncontrado(ENVIO_NO_ENCONTRADO);
        }
        envio.setFechaEntrega(dtoEnvio.getFechaEntrega());
        envio.setFechaCreacion(dtoEnvio.getFechaCreacion());
        return dtoEnvio;
    }

    private void validarFechaOperacionValida(DtoEnvio dtoEnvio) {
        LocalDateTime fechaMinimaOperacion = LocalDateTime.now().plusDays(DIAS_OPERACION);
        if (dtoEnvio.getFechaEntrega().compareTo(fechaMinimaOperacion) < 0) {
            throw new ExcepcionOperacionInvalida(ENVIO_EN_PROCESO_OPERACION_INVALIDA);
        }
    }

}
