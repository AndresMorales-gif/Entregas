package com.ceiba.zona.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.zona.modelo.dto.DtoZona;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoZona implements RowMapper<DtoZona>, MapperResult {
    @Override
    public DtoZona mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        long id = resultSet.getLong("id");
        String nombre = resultSet.getString("nombre");
        int dias = resultSet.getInt("dias_entrega");
        return new DtoZona(id, nombre, dias);
    }
}
