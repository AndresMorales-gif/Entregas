package com.ceiba.zona.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.zona.adaptador.dao.DaoZonaPorIdH2;
import com.ceiba.zona.adaptador.dao.MapeoZona;
import com.ceiba.zona.modelo.dto.DtoZona;
import com.ceiba.zona.puerto.dao.DaoZonaPorId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DaoZonaPorIdH2Test {

    @Test
    @DisplayName("Deberia traer la zona")
    void deberiaTraerLaZona() {
        // arrange
        CustomNamedParameterJdbcTemplate jdbcMock = Mockito.mock(CustomNamedParameterJdbcTemplate.class);
        NamedParameterJdbcTemplate parametrosjdbcMock = Mockito.mock(NamedParameterJdbcTemplate.class);

        Mockito.when(parametrosjdbcMock.queryForObject(Mockito.any(), Mockito.any(MapSqlParameterSource.class), Mockito.any(MapeoZona.class)))
                .thenReturn(new DtoZona(1L, "test", 5));

        Mockito.when(jdbcMock.getNamedParameterJdbcTemplate()).thenReturn(parametrosjdbcMock);
        DaoZonaPorId daoZonaPorId = new DaoZonaPorIdH2(jdbcMock);
        // act
        DtoZona ZonaPrueba = daoZonaPorId.encontrarZonaPorId(1L);
        // assert
        assertEquals(1L, ZonaPrueba.getId());
    }

    @Test
    @DisplayName("Deberia devolver null por no encontrar la zona")
    void deberiaTraerNull() {
        // arrange
        CustomNamedParameterJdbcTemplate jdbcMock = Mockito.mock(CustomNamedParameterJdbcTemplate.class);
        NamedParameterJdbcTemplate parametrosjdbcMock = Mockito.mock(NamedParameterJdbcTemplate.class);

        Mockito.when(parametrosjdbcMock.queryForObject(Mockito.any(), Mockito.any(MapSqlParameterSource.class), Mockito.any(MapeoZona.class)))
                .thenThrow(new EmptyResultDataAccessException(1));

        Mockito.when(jdbcMock.getNamedParameterJdbcTemplate()).thenReturn(parametrosjdbcMock);
        DaoZonaPorId daoZonaPorId = new DaoZonaPorIdH2(jdbcMock);
        // act
        DtoZona zonaTest = daoZonaPorId.encontrarZonaPorId(1L);
        // assert
        assertNull(zonaTest);
    }

}
