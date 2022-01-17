package com.ceiba.zona.dto;

import com.ceiba.zona.modelo.dto.DtoZona;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DtoZonaTest {

    @Test
    @DisplayName("Deberia crear correctamente el usuario")
    void deberiaCrearCorrectamenteLaZona() {
        //act
        DtoZona zona = new DtoZona(1L, "testZona", 2);
        //assert
        assertEquals(1L, zona.getId());
        assertEquals("testZona", zona.getNombre());
        assertEquals(2, zona.getDiasEntrega());
    }

}
