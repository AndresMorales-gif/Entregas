package com.ceiba.envio.consulta;

import com.ceiba.envio.modelo.dto.DtoEnvio;
import com.ceiba.envio.servicio.ServicioListarEnvios;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarEnvios {

    private final ServicioListarEnvios servicioListarEnvios;

    public ManejadorListarEnvios(ServicioListarEnvios servicioListarEnvios) {
        this.servicioListarEnvios = servicioListarEnvios;
    }

    public List<DtoEnvio> ejecutar(String remitente, String tipoConsulta) {
        return servicioListarEnvios.ejecutar(remitente, tipoConsulta);
    }
}
