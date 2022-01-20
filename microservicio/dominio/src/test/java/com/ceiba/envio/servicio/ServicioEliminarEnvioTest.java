package com.ceiba.envio.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionNoEncontrado;
import com.ceiba.dominio.excepcion.ExcepcionOperacionInvalida;
import com.ceiba.envio.modelo.dto.DtoEnvio;
import com.ceiba.envio.puerto.dao.DaoEnvioPorId;
import com.ceiba.envio.puerto.repositorio.RepositorioEnvio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static com.ceiba.envio.servicio.base.ServicioBasePrueba.*;

class ServicioEliminarEnvioTest {

    RepositorioEnvio repositorioEnvio;
    DaoEnvioPorId daoEnvioPorId;

    public ServicioEliminarEnvioTest() {
        repositorioEnvio = Mockito.mock(RepositorioEnvio.class);
        daoEnvioPorId = Mockito.mock(DaoEnvioPorId.class);
    }

    @Test
    @DisplayName("Deberia actualizar correctamente en el repositorio actualizando el precio y fecha con envio plus")
    void deberiaActualizarCorrectamenteEnElRepositorioCambiandoPrecioEnvioPlus() {
        // arrange
        resetMocks();
        Long id = 1L;
        DtoEnvio envioMock = obtenerDataEnvio();

        Mockito.when(daoEnvioPorId.encontrarPorId(Mockito.anyLong())).thenReturn(envioMock);
        ServicioEliminarEnvio servicioEliminarEnvio = new ServicioEliminarEnvio(repositorioEnvio, daoEnvioPorId);
        // act
        servicioEliminarEnvio.ejecutar(id);
        //assert
        Mockito.verify(repositorioEnvio,Mockito.times(1)).eliminar(id);
    }

    @Test
    @DisplayName("Deberia validar que el envio exista previamente")
    void deberiaValidarEnvioExistaPreviamente() {
        // arrange
        resetMocks();
        Long id = 1L;
        Mockito.when(daoEnvioPorId.encontrarPorId(Mockito.anyLong())).thenReturn(null);
        ServicioEliminarEnvio servicioEliminarEnvio = new ServicioEliminarEnvio(repositorioEnvio, daoEnvioPorId);
        // act - assert
        BasePrueba.assertThrows(() -> servicioEliminarEnvio.ejecutar(id), ExcepcionNoEncontrado.class,"El envio no se encontro en el sistema");
    }

    @Test
    @DisplayName("Deberia validar que el envio no este en proceso")
    void deberiaValidarFechaValidaParaEliminarlo() {
        // arrange
        resetMocks();
        Long id = 1L;
        DtoEnvio envioMock = new DtoEnvio(1L, 1L, 2L, 1L, Boolean.FALSE,
                15L, LocalDateTime.now().plusDays(1L).withHour(8).withMinute(0).withSecond(0).withNano(0), 150.0, LocalDateTime.now());

        Mockito.when(daoEnvioPorId.encontrarPorId(Mockito.anyLong())).thenReturn(envioMock);
        ServicioEliminarEnvio servicioEliminarEnvio = new ServicioEliminarEnvio(repositorioEnvio, daoEnvioPorId);
        // act - assert
        BasePrueba.assertThrows(() -> servicioEliminarEnvio.ejecutar(id), ExcepcionOperacionInvalida.class,"El envio se encuentra en proceso. No es posible eliminarlo");
    }

    void resetMocks() {
        Mockito.reset(repositorioEnvio, daoEnvioPorId);
    }

}
