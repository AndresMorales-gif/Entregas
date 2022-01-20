package com.ceiba.usuario.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionNoEncontrado;
import com.ceiba.usuario.modelo.dto.DtoUsuario;
import com.ceiba.usuario.puerto.dao.DaoUsuarioPorDocumento;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicioConsultarUsuarioPorDocumentoTest {

    @Test
    @DisplayName("Deberia lanzar una exepcion cuando se valide la existencia del Usuario")
    void deberiaLanzarUnaExepcionCuandoSeValideLaExistenciaDelUsuario() {
        // arrange
        String idDocumento = "123456";
        DaoUsuarioPorDocumento daoUsuarioPorDocumento = Mockito.mock(DaoUsuarioPorDocumento.class);
        Mockito.when(daoUsuarioPorDocumento.encontrarPorDocumento(Mockito.anyString())).thenReturn(null);
        ServicioConsultarUsuarioPorDocumento servicioConsultarUsuarioPorDocumento = new ServicioConsultarUsuarioPorDocumento(daoUsuarioPorDocumento);
        // act - assert
        BasePrueba.assertThrows(() -> servicioConsultarUsuarioPorDocumento.ejecutar(idDocumento), ExcepcionNoEncontrado.class,"El usuario no existe en el sistema");
    }

    @Test
    @DisplayName("Deberia lanzar una exepcion cuando se valide la existencia del Usuario")
    void deberiaConsultarElUsuarioPorDocumento() {
        // arrange
        String idDocumento = "123456";
        DtoUsuario usuario = new DtoUsuario(1l, "test", idDocumento, LocalDateTime.now());

        DaoUsuarioPorDocumento daoUsuarioPorDocumento = Mockito.mock(DaoUsuarioPorDocumento.class);
        Mockito.when(daoUsuarioPorDocumento.encontrarPorDocumento(Mockito.anyString())).thenReturn(usuario);
        ServicioConsultarUsuarioPorDocumento servicioConsultarUsuarioPorDocumento = new ServicioConsultarUsuarioPorDocumento(daoUsuarioPorDocumento);
        // act - assert
        DtoUsuario usuarioTest = servicioConsultarUsuarioPorDocumento.ejecutar(idDocumento);
        assertEquals(1L, usuarioTest.getId());
        assertEquals("test", usuarioTest.getNombre());
    }
}
