package com.ceiba.zona.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.zona.modelo.dto.DtoZona;
import com.ceiba.zona.puerto.dao.DaoZonaPorId;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class DaoZonaPorIdH2 implements DaoZonaPorId {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="zona", value="encontrarPorId")
    private static String sqlEncontartPorId;

    public DaoZonaPorIdH2(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public DtoZona encontrarZonaPorId(Long zona) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", zona);
        try {
            return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlEncontartPorId,paramSource, new MapeoZona());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
}
