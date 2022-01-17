package com.ceiba.usuario.adaptador.dao;

import com.ceiba.infraestructura.excepcion.ExcepcionNoEncontrado;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.usuario.modelo.dto.DtoUsuario;
import com.ceiba.usuario.puerto.dao.DaoUsuarioPorDocumento;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class DaoUsuarioPorDocumentoH2 implements DaoUsuarioPorDocumento {

    private static final String EL_USUARIO_NO_EXISTE_EN_EL_SISTEMA = "El usuario no existe en el sistema";
    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="usuario", value="encontrarPorDocumento")
    private static String sqlEncontrarPorDocumento;

    public DaoUsuarioPorDocumentoH2(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public DtoUsuario encontrarPorDocumento(String idDocumento) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idDocumento", idDocumento);
        try {
            return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlEncontrarPorDocumento,paramSource, new MapeoUsuario());

        } catch (EmptyResultDataAccessException ex) {
            throw new ExcepcionNoEncontrado(EL_USUARIO_NO_EXISTE_EN_EL_SISTEMA);
        }
    }
}
