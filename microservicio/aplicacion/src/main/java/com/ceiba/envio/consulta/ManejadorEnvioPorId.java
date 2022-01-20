package com.ceiba.envio.consulta;

import com.ceiba.envio.modelo.dto.DtoEnvio;
import com.ceiba.envio.servicio.ServicioConsultarEnvioPorId;
import org.springframework.stereotype.Component;

@Component
public class ManejadorEnvioPorId {

    private final ServicioConsultarEnvioPorId servicioConsultarEnvioPorId;

    public ManejadorEnvioPorId(ServicioConsultarEnvioPorId servicioConsultarEnvioPorId) {
        this.servicioConsultarEnvioPorId = servicioConsultarEnvioPorId;
    }

    public DtoEnvio ejecutar(Long id) {
        return this.servicioConsultarEnvioPorId.ejecutar(id);
    }
}
