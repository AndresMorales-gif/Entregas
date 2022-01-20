package com.ceiba.usuario.dto;

import com.ceiba.usuario.modelo.dto.DtoUsuario;
import com.ceiba.zona.modelo.dto.DtoZona;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DtoUsuarioTest {

    @Test
    @DisplayName("Deberia crear correctamente el usuario")
    void deberiaCrearCorrectamenteLaZona() {
        //act
        DtoUsuario usuario = new DtoUsuario(1L, "testUsuario", "123456", LocalDateTime.now());
        //assert
        assertEquals(1L, usuario.getId());
        assertEquals("testUsuario", usuario.getNombre());
        assertEquals("123456", usuario.getIdDocumento());
        assertNotNull(usuario.getFechaCreacion());
    }

}
