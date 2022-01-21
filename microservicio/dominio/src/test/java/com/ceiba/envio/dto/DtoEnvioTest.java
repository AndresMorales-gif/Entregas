package com.ceiba.envio.dto;

import com.ceiba.envio.modelo.dto.DtoEnvio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DtoEnvioTest {

    @Test
    @DisplayName("Deberia crear correctamente el envio")
    void deberiaCrearCorrectamenteElEnvio() {
        //arrange - act
        DtoEnvio envio = new DtoEnvio(1L, 1L, 2L,
                1L, Boolean.TRUE, 15L, LocalDateTime.now().plusDays(3L), 150.0, LocalDateTime.now());
        //assert
        assertEquals(1L, envio.getId());
        assertEquals(1L, envio.getRemitente());
        assertEquals(2L, envio.getDestinatario());
        assertEquals(1L, envio.getZona());
        assertEquals(Boolean.TRUE, envio.getEnvioPlus());
        assertEquals(15L, envio.getPesoCarga());
        assertNotNull(envio.getFechaEntrega());
        assertEquals(150.0, envio.getPrecio());
        assertNotNull(envio.getFechaCreacion());
    }

}
