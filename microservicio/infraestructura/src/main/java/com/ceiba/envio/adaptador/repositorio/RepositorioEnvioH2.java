package com.ceiba.envio.adaptador.repositorio;

import com.ceiba.envio.modelo.entidad.Envio;
import com.ceiba.envio.puerto.repositorio.RepositorioEnvio;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioEnvioH2 implements RepositorioEnvio {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="envio", value="crear")
    private static String sqlCrear;

    @SqlStatement(namespace="envio", value="actualizar")
    private static String sqlActualizar;

    @SqlStatement(namespace="envio", value="eliminar")
    private static String sqlEliminar;


    public RepositorioEnvioH2(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Envio envio) {
        return this.customNamedParameterJdbcTemplate.crear(envio, sqlCrear);
    }

    @Override
    public void actualizar(Envio envio) {
        this.customNamedParameterJdbcTemplate.actualizar(envio, sqlActualizar);
    }

    @Override
    public void eliminar(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar, paramSource);
    }

}
