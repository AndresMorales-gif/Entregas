package com.ceiba.configuracion;

import com.ceiba.envio.puerto.dao.DaoEnvioListarEntreFechas;
import com.ceiba.envio.puerto.dao.DaoEnvioPorId;
import com.ceiba.envio.puerto.repositorio.RepositorioEnvio;
import com.ceiba.envio.servicio.*;
import com.ceiba.usuario.puerto.dao.DaoUsuarioPorDocumento;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.usuario.servicio.ServicioActualizarUsuario;
import com.ceiba.usuario.servicio.ServicioConsultarUsuarioPorDocumento;
import com.ceiba.usuario.servicio.ServicioCrearUsuario;
import com.ceiba.usuario.servicio.ServicioEliminarUsuario;
import com.ceiba.zona.puerto.dao.DaoZonaPorId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioCrearUsuario servicioCrearUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioCrearUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioEliminarUsuario servicioEliminarUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioEliminarUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioActualizarUsuario servicioActualizarUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioActualizarUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioConsultarUsuarioPorDocumento servicioConsultarUsuarioPorDocumento(DaoUsuarioPorDocumento daoUsuarioPorDocumento) {
        return new ServicioConsultarUsuarioPorDocumento(daoUsuarioPorDocumento);
    }

    @Bean
    public ServicioCrearEnvio servicioCrearEnvio(RepositorioEnvio repositorioEnvio, RepositorioUsuario repositorioUsuario, DaoZonaPorId daoZonaPorId) {
        return new ServicioCrearEnvio(repositorioEnvio, repositorioUsuario, daoZonaPorId);
    }

    @Bean
    public ServicioActualizarEnvio servicioActualizarEnvio(RepositorioEnvio repositorioEnvio, RepositorioUsuario repositorioUsuario, DaoZonaPorId daoZonaPorId, DaoEnvioPorId daoEnvioPorId) {
        return new ServicioActualizarEnvio(repositorioEnvio, repositorioUsuario, daoZonaPorId, daoEnvioPorId);
    }

    @Bean
    public ServicioEliminarEnvio servicioEliminarEnvio(RepositorioEnvio repositorioEnvio, DaoEnvioPorId daoEnvioPorId) {
        return new ServicioEliminarEnvio(repositorioEnvio, daoEnvioPorId);
    }

    @Bean
    public ServicioListarEnvios servicioListarEnvios(DaoEnvioListarEntreFechas daoEnvioListarEntreFechas) {
        return new ServicioListarEnvios(daoEnvioListarEntreFechas);
    }

    @Bean
    public ServicioConsultarEnvioPorId servicioConsultarEnvioPorId(DaoEnvioPorId daoEnvioPorId) {
        return new ServicioConsultarEnvioPorId(daoEnvioPorId);
    }
	

}
