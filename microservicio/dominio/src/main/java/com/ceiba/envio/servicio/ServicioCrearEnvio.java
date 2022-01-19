package com.ceiba.envio.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionNoEncontrado;
import com.ceiba.envio.modelo.entidad.Envio;
import com.ceiba.envio.puerto.repositorio.RepositorioEnvio;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.zona.modelo.dto.DtoZona;
import com.ceiba.zona.puerto.dao.DaoZonaPorId;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class ServicioCrearEnvio extends ServicioBase {

    private static final String EL_DESTINATARIO = "El destinatario";
    private static final String EL_REMITENTE = "El remitente";


    private final RepositorioEnvio repositorioEnvio;


    public ServicioCrearEnvio(RepositorioEnvio repositorioEnvio, RepositorioUsuario repositorioUsuario, DaoZonaPorId daoZonaPorId) {
        super(repositorioUsuario, daoZonaPorId);
        this.repositorioEnvio = repositorioEnvio;
    }

    public Long ejecutar(Envio envio) {
        validarDestinatarioDiferenteRemitente(envio);
        validarExistenciaPreviaUsuario(envio.getRemitente(), EL_REMITENTE);
        validarExistenciaPreviaUsuario(envio.getDestinatario(), EL_DESTINATARIO);
        DtoZona zona = encontrarZona(envio.getZona());
        calcularFechaEntrega(zona, envio);
        calcularPrecio(envio);
        return this.repositorioEnvio.crear(envio);
    }

}
