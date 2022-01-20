package com.ceiba.envio.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionNoEncontrado;
import com.ceiba.envio.modelo.dto.DtoEnvio;
import com.ceiba.envio.puerto.dao.DaoEnvioPorId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.ceiba.envio.servicio.base.ServicioBasePrueba.obtenerDataEnvio;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ServicioConsultaEnvioPorIdTest {

    @Test
    @DisplayName("Deberia traer el envio por id")
    void deberialistarEnviosPendientes() {
        // arrange
        Long id = 1L;
        DtoEnvio envioMock = obtenerDataEnvio();
        DaoEnvioPorId daoEnvioPorId = Mockito.mock(DaoEnvioPorId.class);
        Mockito.when(daoEnvioPorId.encontrarPorId(Mockito.anyLong())).thenReturn(envioMock);
        ServicioConsultarEnvioPorId servicioConsultarEnvioPorId = new ServicioConsultarEnvioPorId(daoEnvioPorId);
        // act
        DtoEnvio envio = servicioConsultarEnvioPorId.ejecutar(id);
        //assert
        assertEquals(envioMock, envio);
    }

    @Test
    @DisplayName("Deberia validar que envio exista")
    void deberiaValidarQueEnvioExista() {
        // arrange
        Long id = 1L;
        DaoEnvioPorId daoEnvioPorId = Mockito.mock(DaoEnvioPorId.class);
        Mockito.when(daoEnvioPorId.encontrarPorId(Mockito.anyLong())).thenReturn(null);
        ServicioConsultarEnvioPorId servicioConsultarEnvioPorId = new ServicioConsultarEnvioPorId(daoEnvioPorId);
        // act - assert
        BasePrueba.assertThrows(() -> servicioConsultarEnvioPorId.ejecutar(id), ExcepcionNoEncontrado.class, "El envio no existe en el sistema");
    }

}
