package com.ceiba.envio.controlador;

import com.ceiba.ApplicationMock;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsultaControladorEnvio.class)
@ContextConfiguration(classes= ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ConsultaControladorEnvioTest {

    @Autowired
    private MockMvc mocMvc;

    @Test
    @DisplayName("Deberia listar envios pendientes")
    void deberiaListarEnviosPendiente() throws Exception {
        String remitente = "123456";
        mocMvc.perform(get("/envios/usuario/{remitente}", remitente)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].pesoCarga", is(15)));

    }

    @Test
    @DisplayName("Deberia listar envios en proceso vacio")
    void deberiaListarEnviosEnProcesoVacio() throws Exception {
        // arrange
        Long remitente = 1L;
        Long consulta = 2L;
        // act - assert
        mocMvc.perform(get("/envios/usuario/{remitente}?consulta={tipo}", remitente, consulta)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

    }

    @Test
    @DisplayName("Deberia encontrar envio por Id")
    void deberiaEncontrarEnvioPorId() throws Exception {
        // arrange
        Long id = 1L;
        // act - assert
        mocMvc.perform(get("/envios/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pesoCarga", is(15)))
                .andExpect(jsonPath("$.remitente", is("123456")));

    }

    @Test
    @DisplayName("Deberia fallar por id inexistente")
    void deberiaFallarEnvioPorIdInexistente() throws Exception {
        // arrange
        Long id = 2L;
        // act - assert
        mocMvc.perform(get("/envios/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.nombreExcepcion", is("ExcepcionNoEncontrado")))
                .andExpect(jsonPath("$.mensaje", is("El envio no existe en el sistema")));

    }

}
