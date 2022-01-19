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

    private final DaoEnvioListarEntreFechas daoEnvioListarEntreFechas;

    public ServicioListarEnvios(DaoEnvioListarEntreFechas daoEnvioListarEntreFechas) {
        this.daoEnvioListarEntreFechas = daoEnvioListarEntreFechas;
    }

    public List<DtoEnvio> ejecutar(Long remitente, String tipoConsulta) {
        if (tipoConsulta == null) {
            tipoConsulta = PENDIENTES;
        }
        LocalDateTime fechaInicio = null;
        LocalDateTime fechaFinal = null;
        switch (tipoConsulta) {
            case PENDIENTES:
                fechaInicio = LocalDateTime.now().plusDays(DIAS_EN_PROCESO);
                break;
            case EN_PROCESO:
                fechaInicio = LocalDateTime.now();
                fechaFinal = LocalDateTime.now().plusDays(DIAS_EN_PROCESO);
                break;
            case HISTORICO:
                fechaFinal = LocalDateTime.now();
                break;
            default:
                throw new ExcepcionValorInvalido(VALOR_CONSULTA_INVALIDO);
        }
        return this.daoEnvioListarEntreFechas.listarEntreFechas(remitente, fechaInicio, fechaFinal);
    }
}
