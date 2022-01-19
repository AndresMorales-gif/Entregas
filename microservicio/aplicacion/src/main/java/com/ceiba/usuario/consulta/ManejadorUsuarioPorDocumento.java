package com.ceiba.usuario.consulta;

import com.ceiba.usuario.modelo.dto.DtoUsuario;
import com.ceiba.usuario.puerto.dao.DaoUsuarioPorDocumento;
import com.ceiba.usuario.servicio.ServicioConsultarUsuarioPorDocumento;
import org.springframework.stereotype.Component;

@Component
public class ManejadorUsuarioPorDocumento {

    private final ServicioConsultarUsuarioPorDocumento servicioConsultarUsuarioPorDocumento;

    public ManejadorUsuarioPorDocumento(ServicioConsultarUsuarioPorDocumento servicioConsultarUsuarioPorDocumento) {
        this.servicioConsultarUsuarioPorDocumento = servicioConsultarUsuarioPorDocumento;
    }

    public DtoUsuario ejecutar(String idDocumento) {
        return servicioConsultarUsuarioPorDocumento.ejecutar(idDocumento);
    }
}
