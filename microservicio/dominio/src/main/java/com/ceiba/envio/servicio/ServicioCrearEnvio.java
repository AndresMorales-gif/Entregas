package com.ceiba.envio.servicio;

import com.ceiba.envio.modelo.entidad.Envio;
import com.ceiba.envio.puerto.repositorio.RepositorioEnvio;
import com.ceiba.envio.servicio.base.ServicioBaseEnvio;
import com.ceiba.usuario.puerto.dao.DaoUsuarioPorDocumento;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.zona.modelo.dto.DtoZona;
import com.ceiba.zona.puerto.dao.DaoZonaPorId;

public class ServicioCrearEnvio extends ServicioBaseEnvio {

    private final RepositorioEnvio repositorioEnvio;


    public ServicioCrearEnvio(RepositorioEnvio repositorioEnvio, DaoUsuarioPorDocumento daoUsuarioPorDocumento, DaoZonaPorId daoZonaPorId) {
        super(daoUsuarioPorDocumento, daoZonaPorId);
        this.repositorioEnvio = repositorioEnvio;
    }

    public Long ejecutar(Envio envio) {
        validarExistenciaPreviaUsuario(envio.getRemitente(), EL_REMITENTE);
        validarExistenciaPreviaUsuario(envio.getDestinatario(), EL_DESTINATARIO);
        DtoZona zona = encontrarZona(envio.getZona());
        calcularFechaEntrega(zona, envio);
        calcularPrecio(envio);
        return this.repositorioEnvio.crear(envio);
    }

}
