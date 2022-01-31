package com.ceiba.envio.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.envio.modelo.entidad.Envio;
import com.ceiba.envio.servicio.testdatabuilder.EnvioTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EnvioTest {

    @Test
    @DisplayName("Deberia crear correctamente el envio")
    void deberiaCrearCorrectamenteElEnvio() {
        //arrange - act
        Envio envio = new EnvioTestDataBuilder().conId(1L).conEnvioPlus(null).build();
        //assert
        assertEquals(1, envio.getId());
        assertEquals("123456", envio.getRemitente());
        assertEquals("654321", envio.getDestinatario());
        assertEquals(Boolean.FALSE, envio.getEnvioPlus());
        assertNotNull(envio.getFechaCreacion());
    }

    @Test
    void deberiaFallarSinRemitenteDeEnvio() {
        //Arrange
        EnvioTestDataBuilder envioTestDataBuilder = new EnvioTestDataBuilder().conRemitente(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    envioTestDataBuilder.build();
            },
            ExcepcionValorObligatorio.class, "Se debe ingresar el remitente del envio");
    }

    @Test
    void deberiaFallarSinDestinatarioDeEnvio() {
        //Arrange
        EnvioTestDataBuilder envioTestDataBuilder = new EnvioTestDataBuilder().conDestinatario(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    envioTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el destinatario del envio");
    }

    @Test
    void deberiaFallarSinZonaDeEnvio() {
        //Arrange
        EnvioTestDataBuilder envioTestDataBuilder = new EnvioTestDataBuilder().conZona(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    envioTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar la zona del envio");
    }

    @Test
    void deberiaFallarSinPesoCargaDeEnvio() {
        //Arrange
        EnvioTestDataBuilder envioTestDataBuilder = new EnvioTestDataBuilder().conPesoCarga(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    envioTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el peso de la carga del envio");
    }

    @Test
    void deberiaFallarRemitenteYDestinatarioIgualesDeEnvio() {
        //Arrange
        EnvioTestDataBuilder envioTestDataBuilder = new EnvioTestDataBuilder().conDestinatario("123456");
        //act-assert
        BasePrueba.assertThrows(() -> {
                    envioTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "El remitente no puede ser el mismo que el destinatario");
    }
}
