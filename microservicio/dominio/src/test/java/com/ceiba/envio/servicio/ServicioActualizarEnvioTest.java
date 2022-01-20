package com.ceiba.envio.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionNoEncontrado;
import com.ceiba.dominio.excepcion.ExcepcionOperacionInvalida;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.envio.modelo.dto.DtoEnvio;
import com.ceiba.envio.modelo.entidad.Envio;
import com.ceiba.envio.puerto.dao.DaoEnvioPorId;
import com.ceiba.envio.puerto.repositorio.RepositorioEnvio;
import com.ceiba.envio.servicio.testdatabuilder.EnvioTestDataBuilder;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.zona.modelo.dto.DtoZona;
import com.ceiba.zona.puerto.dao.DaoZonaPorId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static com.ceiba.envio.servicio.base.ServicioBasePrueba.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ServicioActualizarEnvioTest {

    RepositorioEnvio repositorioEnvio;
    RepositorioUsuario repositorioUsuario;
    DaoZonaPorId daoZonaPorId;
    DaoEnvioPorId daoEnvioPorId;

    public ServicioActualizarEnvioTest() {
        repositorioEnvio = Mockito.mock(RepositorioEnvio.class);
        repositorioUsuario = Mockito.mock(RepositorioUsuario.class);
        daoZonaPorId = Mockito.mock(DaoZonaPorId.class);
        daoEnvioPorId = Mockito.mock(DaoEnvioPorId.class);
    }

    @Test
    @DisplayName("Deberia actualizar correctamente en el repositorio actualizando el precio y fecha con envio plus")
    void deberiaActualizarCorrectamenteEnElRepositorioCambiandoPrecioEnvioPlus() {
        // arrange
        resetMocks();
        Envio envio = new EnvioTestDataBuilder().conId(1L).conEnvioPlus(Boolean.TRUE).conDestinatario(3L).build();
        DtoEnvio envioMock = obtenerDataEnvio();
        DtoZona zona = obtenerZona();

        Mockito.when(daoEnvioPorId.encontrarPorId(Mockito.anyLong())).thenReturn(envioMock);
        Mockito.when(repositorioUsuario.existePorId(Mockito.anyLong())).thenReturn(true);
        Mockito.when(daoZonaPorId.encontrarZonaPorId(Mockito.anyLong())).thenReturn(zona);
        ServicioActualizarEnvio servicioActualizarEnvio = new ServicioActualizarEnvio(repositorioEnvio, repositorioUsuario, daoZonaPorId, daoEnvioPorId);
        // act
        servicioActualizarEnvio.ejecutar(envio);
        //assert
        assertEquals(195,envio.getPrecio());
        assertTrue(validarFechaEntrega(envio.getFechaEntrega(), zona.getDiasEntrega(), envio.getEnvioPlus()));
        Mockito.verify(repositorioEnvio,Mockito.times(1)).actualizar(envio);
    }

    @Test
    @DisplayName("Deberia actualizar correctamente en el repositorio y cambiar fecha de entrega al cambiar la zona")
    void deberiaActualizarCorrectamenteEnElRepositorioCambiandoZona() {
        // arrange
        resetMocks();
        Envio envio = new EnvioTestDataBuilder().conId(1L).conZona(2L).build();
        DtoEnvio envioMock = obtenerDataEnvio();
        DtoZona zona = new DtoZona(2L, "test", 9);

        Mockito.when(daoEnvioPorId.encontrarPorId(Mockito.anyLong())).thenReturn(envioMock);
        Mockito.when(repositorioUsuario.existePorId(Mockito.anyLong())).thenReturn(true);
        Mockito.when(daoZonaPorId.encontrarZonaPorId(Mockito.anyLong())).thenReturn(zona);
        ServicioActualizarEnvio servicioActualizarEnvio = new ServicioActualizarEnvio(repositorioEnvio, repositorioUsuario, daoZonaPorId, daoEnvioPorId);
        // act
        servicioActualizarEnvio.ejecutar(envio);
        //assert
        assertTrue(validarFechaEntrega(envio.getFechaEntrega(), zona.getDiasEntrega(), envio.getEnvioPlus()));
        Mockito.verify(repositorioEnvio,Mockito.times(1)).actualizar(envio);
    }

    @Test
    @DisplayName("Deberia validar que el remitente sea el mismo")
    void deberiaValidarQueElRemitenteNoCambie() {
        // arrange
        resetMocks();
        Envio envio = new EnvioTestDataBuilder().conId(1L).conRemitente(3L).build();
        DtoEnvio envioMock = obtenerDataEnvio();

        Mockito.when(daoEnvioPorId.encontrarPorId(Mockito.anyLong())).thenReturn(envioMock);
        ServicioActualizarEnvio servicioActualizarEnvio = new ServicioActualizarEnvio(repositorioEnvio, repositorioUsuario, daoZonaPorId, daoEnvioPorId);
        // act - assert
        BasePrueba.assertThrows(() -> servicioActualizarEnvio.ejecutar(envio), ExcepcionValorInvalido.class,"El remitente no puede ser diferente");
    }

    @Test
    @DisplayName("Deberia que el destinatario exista previamente")
    void deberiaValidarQueElDestinatarioExista() {
        // arrange
        resetMocks();
        Envio envio = new EnvioTestDataBuilder().conId(1L).conDestinatario(3L).build();
        DtoEnvio envioMock = obtenerDataEnvio();

        Mockito.when(daoEnvioPorId.encontrarPorId(Mockito.anyLong())).thenReturn(envioMock);
        Mockito.when(repositorioUsuario.existePorId(Mockito.anyLong())).thenReturn(false);
        ServicioActualizarEnvio servicioActualizarEnvio = new ServicioActualizarEnvio(repositorioEnvio, repositorioUsuario, daoZonaPorId, daoEnvioPorId);
        // act - assert
        BasePrueba.assertThrows(() -> servicioActualizarEnvio.ejecutar(envio), ExcepcionNoEncontrado.class,"El destinatario no existe en el sistema");
    }

    @Test
    @DisplayName("Deberia validar que el envio no este en proceso")
    void deberiaValidarFechaValidaParaActualizar() {
        // arrange
        resetMocks();
        Envio envio = new EnvioTestDataBuilder().conId(1L).conDestinatario(3L).build();
        DtoEnvio envioMock = new DtoEnvio(1L, 1L, 2L, 1L, Boolean.FALSE,
                15L, LocalDateTime.now().plusDays(2L).withHour(8).withMinute(0).withSecond(0).withNano(0), 150.0, LocalDateTime.now());
        Mockito.when(daoEnvioPorId.encontrarPorId(Mockito.anyLong())).thenReturn(envioMock);
        ServicioActualizarEnvio servicioActualizarEnvio = new ServicioActualizarEnvio(repositorioEnvio, repositorioUsuario, daoZonaPorId, daoEnvioPorId);
        // act - assert
        BasePrueba.assertThrows(() -> servicioActualizarEnvio.ejecutar(envio), ExcepcionOperacionInvalida.class,"El envio se encuentra en proceso. No es posible actualizarlo");
    }

    @Test
    @DisplayName("Deberia validar que el envio exista previamente")
    void deberiaValidarEnvioExistaPreviamente() {
        // arrange
        resetMocks();
        Envio envio = new EnvioTestDataBuilder().conId(1L).build();
        Mockito.when(daoEnvioPorId.encontrarPorId(Mockito.anyLong())).thenReturn(null);
        ServicioActualizarEnvio servicioActualizarEnvio = new ServicioActualizarEnvio(repositorioEnvio, repositorioUsuario, daoZonaPorId, daoEnvioPorId);
        // act - assert
        BasePrueba.assertThrows(() -> servicioActualizarEnvio.ejecutar(envio), ExcepcionNoEncontrado.class,"El envio no se encontro en el sistema");
    }

    void resetMocks() {
        Mockito.reset(repositorioUsuario, repositorioEnvio, daoZonaPorId, daoEnvioPorId);
    }

}
