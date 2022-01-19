package com.ceiba.envio.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionNoEncontrado;
import com.ceiba.envio.modelo.entidad.Envio;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.zona.modelo.dto.DtoZona;
import com.ceiba.zona.puerto.dao.DaoZonaPorId;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class ServicioBase {

    private static final String EL_REMITENTE_DESTINATARIO_IGUAL = "El remitente no puede ser el mismo que el destinatario";
    private static final String EL_DATO_NO_EXISTE_EN_EL_SISTEMA = "%s no existe en el sistema";
    private static final String LA_ZONA = "La zona";

    private static final int COMPARACION_IGUAL = 0;
    private static final Integer PRECIO_X_KILO = 10;
    private static final Double PORCENTAJE_ENVIO_PLUS = 1.3;
    private static final Integer DIVIDIR_TIEMPO_PLUS = 2;
    private static final Integer CIFRA_REDONDEAR_ARRIBA = 1;
    private static final Long UN_DIA = 1L;
    private static final Long DOS_DIAS = 2L;

    private final RepositorioUsuario repositorioUsuario;
    private  final DaoZonaPorId daoZonaPorId;

    public ServicioBase(RepositorioUsuario repositorioUsuario, DaoZonaPorId daoZonaPorId) {
        this.repositorioUsuario = repositorioUsuario;
        this.daoZonaPorId = daoZonaPorId;
    }

    protected void validarDestinatarioDiferenteRemitente(Envio envio) {
        if (envio.getDestinatario().compareTo(envio.getRemitente()) == COMPARACION_IGUAL) {
            throw new ExcepcionDuplicidad(EL_REMITENTE_DESTINATARIO_IGUAL);
        }
    }

    protected void validarExistenciaPreviaUsuario(Long idUsuario, String usuario) {
        boolean existe = this.repositorioUsuario.existePorId(idUsuario);
        if(!existe) {
            throw new ExcepcionNoEncontrado(String.format(EL_DATO_NO_EXISTE_EN_EL_SISTEMA, usuario));
        }
    }

    protected DtoZona encontrarZona(Long idZona) {
        DtoZona zona = this.daoZonaPorId.encontrarZonaPorId(idZona);
        if(zona == null) {
            throw new ExcepcionNoEncontrado(String.format(EL_DATO_NO_EXISTE_EN_EL_SISTEMA, LA_ZONA));
        }
        return zona;
    }

    protected void calcularFechaEntrega(DtoZona zona, Envio envio) {
        Integer diasEntrega = zona.getDiasEntrega();
        if (envio.getEnvioPlus()) {
            diasEntrega += CIFRA_REDONDEAR_ARRIBA;
            diasEntrega = diasEntrega / DIVIDIR_TIEMPO_PLUS;
        }
        LocalDateTime fechaEntrega = LocalDateTime.now().plusDays(diasEntrega);
        if (fechaEntrega.getDayOfWeek() == DayOfWeek.SATURDAY) {
            fechaEntrega = fechaEntrega.plusDays(DOS_DIAS);
        }
        if (fechaEntrega.getDayOfWeek() == DayOfWeek.SUNDAY) {
            fechaEntrega = fechaEntrega.plusDays(UN_DIA);
        }
        envio.setFechaEntrega(fechaEntrega);
    }

    protected void calcularPrecio(Envio envio) {
        double precio = (double) (envio.getPesoCarga() * PRECIO_X_KILO);
        if (envio.getEnvioPlus()) {
            precio = precio * PORCENTAJE_ENVIO_PLUS;
        }
        envio.setPrecio(precio);
    }

}
