package com.ceiba.envio.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.envio.modelo.dto.DtoEnvio;
import com.ceiba.envio.puerto.dao.DaoEnvioListarEntreFechas;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.ceiba.envio.servicio.base.ServicioBasePrueba.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ServicioListarEnviosTest {

    @Test
    @DisplayName("Deberia listar envios pendiente")
    void deberialistarEnviosPendientes() {
        // arrange
        String remitente = "123456";
        List<DtoEnvio> envios = new ArrayList<>();
        envios.add(obtenerDataEnvio());
        envios.add(obtenerDataEnvio());
        DaoEnvioListarEntreFechas daoEnvioListarEntreFechas = Mockito.mock(DaoEnvioListarEntreFechas.class);
        Mockito.when(daoEnvioListarEntreFechas.listarEntreFechas(Mockito.anyString(), Mockito.any(LocalDateTime.class), Mockito.any())).thenReturn(envios);
        ServicioListarEnvios servicioListarEnvios = new ServicioListarEnvios(daoEnvioListarEntreFechas);
        // act
        List<DtoEnvio> enviosPrueba = servicioListarEnvios.ejecutar(remitente, null);
        //assert
        assertEquals(2, enviosPrueba.size());
    }

    @Test
    @DisplayName("Deberia listar envios en proceso vacio")
    void deberialistarEnviosEnProcesoVacio() {
        // arrange
        String remitente = "123456";
        String tipoConsulta = "2";
        List<DtoEnvio> envios = new ArrayList<>();
        DaoEnvioListarEntreFechas daoEnvioListarEntreFechas = Mockito.mock(DaoEnvioListarEntreFechas.class);
        Mockito.when(daoEnvioListarEntreFechas.listarEntreFechas(Mockito.anyString(), Mockito.any(LocalDateTime.class), Mockito.any(LocalDateTime.class))).thenReturn(envios);
        ServicioListarEnvios servicioListarEnvios = new ServicioListarEnvios(daoEnvioListarEntreFechas);
        // act
        List<DtoEnvio> enviosPrueba = servicioListarEnvios.ejecutar(remitente, tipoConsulta);
        //assert
        assertEquals(0, enviosPrueba.size());
    }

    @Test
    @DisplayName("Deberia listar historico envios")
    void deberialistarHistoricoEnvios() {
        // arrange
        String remitente = "123456";
        String tipoConsulta = "3";
        List<DtoEnvio> envios = new ArrayList<>();
        envios.add(obtenerDataEnvio());
        DaoEnvioListarEntreFechas daoEnvioListarEntreFechas = Mockito.mock(DaoEnvioListarEntreFechas.class);
        Mockito.when(daoEnvioListarEntreFechas.listarEntreFechas(Mockito.anyString(), Mockito.any(), Mockito.any())).thenReturn(envios);
        ServicioListarEnvios servicioListarEnvios = new ServicioListarEnvios(daoEnvioListarEntreFechas);
        // act
        List<DtoEnvio> enviosPrueba = servicioListarEnvios.ejecutar(remitente, tipoConsulta);
        //assert
        assertEquals(1, enviosPrueba.size());
    }

    @Test
    @DisplayName("Deberia validar el valor de tipo consulta")
    void deberiaValidarValorTipoConsulta() {
        // arrange
        String remitente = "123456";
        String tipoConsulta = "4";
        List<DtoEnvio> envios = new ArrayList<>();
        DaoEnvioListarEntreFechas daoEnvioListarEntreFechas = Mockito.mock(DaoEnvioListarEntreFechas.class);
        Mockito.when(daoEnvioListarEntreFechas.listarEntreFechas(Mockito.anyString(), Mockito.any(), Mockito.any())).thenReturn(envios);
        ServicioListarEnvios servicioListarEnvios = new ServicioListarEnvios(daoEnvioListarEntreFechas);
        // act - assert
        BasePrueba.assertThrows(() -> servicioListarEnvios.ejecutar(remitente, tipoConsulta), ExcepcionValorInvalido.class,"El valor del parametro consulta no es correcto");
    }
}
