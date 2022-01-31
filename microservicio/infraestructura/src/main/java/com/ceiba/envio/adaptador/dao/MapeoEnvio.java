package com.ceiba.envio.adaptador.dao;

import com.ceiba.envio.modelo.dto.DtoEnvio;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MapeoEnvio implements RowMapper<DtoEnvio>, MapperResult {
    @Override
    public DtoEnvio mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        String remitente = resultSet.getString("remitente");
        String destinatario = resultSet.getString("destinatario");
        Long zona = resultSet.getLong("zona");
        Boolean envioPlus = resultSet.getBoolean("envio_plus");
        Long pesoCarga = resultSet.getLong("peso_carga");
        LocalDateTime fechaEntrega = extraerLocalDateTime(resultSet, "fecha_entrega");
        Double precio = resultSet.getDouble("precio");
        LocalDateTime fechaCreacion = extraerLocalDateTime(resultSet,"fecha_creacion");
        return new DtoEnvio(id,
                remitente,
                destinatario,
                zona,
                envioPlus,
                pesoCarga,
                fechaEntrega,
                precio,
                fechaCreacion);
    }
}
