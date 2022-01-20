package com.ceiba.envio.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.envio.comando.ComandoEnvio;
import com.ceiba.envio.servicio.testdatabuilder.ComandEnvioTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorEnvio.class)
@ContextConfiguration(classes= ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ComandoControladorEnvioTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    @DisplayName("Deberia traer error al crear un envio por destinatario no encontrado")
    void deberiaTraerErrorAlCrearUnEnvioPorDestinatarioNoEncontrado() throws Exception{
        // arrange
        ComandoEnvio envio = new ComandEnvioTestDataBuilder().conDestinatario(3L).build();
        // act - assert
        mocMvc.perform(post("/envios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(envio)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.nombreExcepcion", is("ExcepcionNoEncontrado")))
                .andExpect(jsonPath("$.mensaje", is("El destinatario no existe en el sistema")));
    }

    @Test
    @DisplayName("Deberia traer error al crear un envio por mismo remitente y destinatario")
    void deberiaTraerErrorAlCrearUnEnvioPorMismoRemitenteDestinatario() throws Exception{
        // arrange
        ComandoEnvio envio = new ComandEnvioTestDataBuilder().conDestinatario(1L).build();
        // act - assert
        mocMvc.perform(post("/envios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(envio)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.nombreExcepcion", is("ExcepcionDuplicidad")))
                .andExpect(jsonPath("$.mensaje", is("El remitente no puede ser el mismo que el destinatario")));
    }

    @Test
    @DisplayName("Deberia crear un envio")
    void deberiaCrearUnEnvio() throws Exception{
        // arrange
        ComandoEnvio envio = new ComandEnvioTestDataBuilder().build();
        // act - assert
        mocMvc.perform(post("/envios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(envio)))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"valor\": 2}"));
    }

    @Test
    @DisplayName("Deberia actualizar un envio")
    void deberiaActualizarUnEnvio() throws Exception{
        // arrange
        Long id = 1L;
        ComandoEnvio envio = new ComandEnvioTestDataBuilder()
                        .conEnvioPlus(Boolean.TRUE).conPesoCarga(20L).build();
        // act - assert
        mocMvc.perform(put("/envios/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(envio)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deberia traer error al actualizar un envio cambiando el remitente")
    void deberiaTraerErrorActualizarUnEnvioCambiandoRemitente() throws Exception{
        // arrange
        Long id = 1L;
        ComandoEnvio envio = new ComandEnvioTestDataBuilder().conRemitente(3L).build();
        // act - assert
        mocMvc.perform(put("/envios/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(envio)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.nombreExcepcion", is("ExcepcionValorInvalido")))
                .andExpect(jsonPath("$.mensaje", is("El remitente no puede ser diferente")));
    }

    @Test
    @DisplayName("Deberia eliminar un envio")
    void deberiaEliminarUnEnvio() throws Exception{
        // arrange
        Long id = 1L;
        ComandoEnvio envio = new ComandEnvioTestDataBuilder().conRemitente(3L).build();
        // act - assert
        mocMvc.perform(delete("/envios/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Long remitente = 1L;
        mocMvc.perform(get("/envios/usuario/{remitente}", remitente)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

}
