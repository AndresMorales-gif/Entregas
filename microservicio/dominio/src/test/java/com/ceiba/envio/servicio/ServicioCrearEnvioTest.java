package com.ceiba.envio.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionNoEncontrado;
import com.ceiba.envio.modelo.entidad.Envio;
import com.ceiba.envio.puerto.repositorio.RepositorioEnvio;
import com.ceiba.envio.servicio.testdatabuilder.EnvioTestDataBuilder;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.zona.modelo.dto.DtoZona;
import com.ceiba.zona.puerto.dao.DaoZonaPorId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.ceiba.envio.servicio.base.ServicioBasePrueba.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ServicioCrearEnvioTest {

    RepositorioEnvio repositorioEnvio;
    RepositorioUsuario repositorioUsuario;
    DaoZonaPorId daoZonaPorId;

    public ServicioCrearEnvioTest() {
        repositorioEnvio = Mockito.mock(RepositorioEnvio.class);
        repositorioUsuario = Mockito.mock(RepositorioUsuario.class);
        daoZonaPorId = Mockito.mock(DaoZonaPorId.class);
    }

    @Test
    @DisplayName("Deberia crear correctamente el envio en el repositorio")
    void deberiaCrearCorrectamenteElEnvioEnElRepositorio() {
        // arrange
        resetMocks();
        Envio envio = new EnvioTestDataBuilder().conId(1L).conPesoCarga(10L).build();
        DtoZona zona = obtenerZona();

        Mockito.when(repositorioUsuario.existePorId(Mockito.anyLong())).thenReturn(true);
        Mockito.when(daoZonaPorId.encontrarZonaPorId(Mockito.anyLong())).thenReturn(zona);
        ServicioCrearEnvio servicioCrearEnvio = new ServicioCrearEnvio(repositorioEnvio, repositorioUsuario, daoZonaPorId);
        // act
        servicioCrearEnvio.ejecutar(envio);
        //assert
        assertEquals(100,envio.getPrecio());
        assertTrue(validarFechaEntrega(envio.getFechaEntrega(), zona.getDiasEntrega(), envio.getEnvioPlus()));
        Mockito.verify(repositorioEnvio,Mockito.times(1)).crear(envio);
    }

    @Test
    @DisplayName("Deberia crear correctamente el envio en el repositorio con envio plus")
    void deberiaCrearCorrectamenteElEnvioEnElRepositorioConEnvioPlus() {
        // arrange
        resetMocks();
        Envio envio = new EnvioTestDataBuilder().conId(1L).conPesoCarga(20L).conEnvioPlus(Boolean.TRUE).build();
        DtoZona zona = obtenerZona();

        Mockito.when(repositorioUsuario.existePorId(Mockito.anyLong())).thenReturn(true);
        Mockito.when(daoZonaPorId.encontrarZonaPorId(Mockito.anyLong())).thenReturn(zona);
        ServicioCrearEnvio servicioCrearEnvio = new ServicioCrearEnvio(repositorioEnvio, repositorioUsuario, daoZonaPorId);
        // act
        servicioCrearEnvio.ejecutar(envio);
        //assert
        assertEquals(260,envio.getPrecio());
        assertTrue(validarFechaEntrega(envio.getFechaEntrega(), zona.getDiasEntrega(), envio.getEnvioPlus()));
        Mockito.verify(repositorioEnvio,Mockito.times(1)).crear(envio);
    }

    @Test
    @DisplayName("Deberia validar que el remitente exista previamente")
    void deberiaValidarQueElRemitenteExista() {
        // arrange
        resetMocks();
        Envio envio = new EnvioTestDataBuilder().conId(1L).build();

        Mockito.when(repositorioUsuario.existePorId(1L)).thenReturn(false);
        ServicioCrearEnvio servicioCrearEnvio = new ServicioCrearEnvio(repositorioEnvio, repositorioUsuario, daoZonaPorId);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearEnvio.ejecutar(envio), ExcepcionNoEncontrado.class,"El remitente no existe en el sistema");
    }

    @Test
    @DisplayName("Deberia validar que el destinatario exista previamente")
    void deberiaValidarQueElDestinatarioExista() {
        // arrange
        resetMocks();
        Envio envio = new EnvioTestDataBuilder().conId(1L).build();

        Mockito.when(repositorioUsuario.existePorId(1L)).thenReturn(true);
        Mockito.when(repositorioUsuario.existePorId(2L)).thenReturn(false);
        ServicioCrearEnvio servicioCrearEnvio = new ServicioCrearEnvio(repositorioEnvio, repositorioUsuario, daoZonaPorId);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearEnvio.ejecutar(envio), ExcepcionNoEncontrado.class,"El destinatario no existe en el sistema");
    }

    @Test
    @DisplayName("Deberia validar que la zona exista previamente")
    void deberiaValidarQueLaZonaExista() {
        // arrange
        resetMocks();
        Envio envio = new EnvioTestDataBuilder().conId(1L).build();

        Mockito.when(repositorioUsuario.existePorId(Mockito.anyLong())).thenReturn(true);
        Mockito.when(daoZonaPorId.encontrarZonaPorId(Mockito.anyLong())).thenReturn(null);
        ServicioCrearEnvio servicioCrearEnvio = new ServicioCrearEnvio(repositorioEnvio, repositorioUsuario, daoZonaPorId);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearEnvio.ejecutar(envio), ExcepcionNoEncontrado.class,"La zona no existe en el sistema");
    }

    void resetMocks() {
        Mockito.reset(repositorioUsuario, repositorioEnvio, daoZonaPorId);
    }
}
