package com.ceiba.envio.servicio;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.envio.modelo.dto.DtoEnvio;
import com.ceiba.envio.puerto.dao.DaoEnvioListarEntreFechas;

import java.time.LocalDateTime;
import java.util.List;

public class ServicioListarEnvios {

    private static final String VALOR_CONSULTA_INVALIDO = "El valor del parametro consulta no es correcto";

    private static final String PENDIENTES = "1";
    private static final String EN_PROCESO = "2";
    private static final String HISTORICO = "3";

    private static final Long DIAS_EN_PROCESO = 3L;
    private static final Long UN_DIA = 1L;


    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFinal;

    private final DaoEnvioListarEntreFechas daoEnvioListarEntreFechas;

    public ServicioListarEnvios(DaoEnvioListarEntreFechas daoEnvioListarEntreFechas) {
        this.daoEnvioListarEntreFechas = daoEnvioListarEntreFechas;
    }

    public List<DtoEnvio> ejecutar(String remitente, String tipoConsulta) {
        validarFechas(tipoConsulta);
        return this.daoEnvioListarEntreFechas.listarEntreFechas(remitente, this.fechaInicio, this.fechaFinal);
    }

    private void validarFechas(String tipoConsulta) {
        if (tipoConsulta == null) {
            tipoConsulta = PENDIENTES;
        }
        switch (tipoConsulta) {
            case PENDIENTES:
                fechaInicio = obtenerFecha().plusDays(DIAS_EN_PROCESO + UN_DIA);
                fechaFinal = null;
                break;
            case EN_PROCESO:
                fechaInicio = obtenerFecha();
                fechaFinal = obtenerFecha().plusDays(DIAS_EN_PROCESO);
                break;
            case HISTORICO:
                fechaInicio = null;
                fechaFinal = obtenerFecha().minusDays(UN_DIA);
                break;
            default:
                throw new ExcepcionValorInvalido(VALOR_CONSULTA_INVALIDO);
        }
    }

    private LocalDateTime obtenerFecha() {
        return LocalDateTime.now().withHour(8).withMinute(0).withSecond(0).withNano(0);
    }
}
