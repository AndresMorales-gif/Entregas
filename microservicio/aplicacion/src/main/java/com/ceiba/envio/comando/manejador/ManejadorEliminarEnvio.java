package com.ceiba.envio.comando.manejador;

import com.ceiba.envio.servicio.ServicioEliminarEnvio;
import org.springframework.stereotype.Component;

@Component
public class ManejadorEliminarEnvio {

    private final ServicioEliminarEnvio servicioEliminarEnvio;

    public ManejadorEliminarEnvio(ServicioEliminarEnvio servicioEliminarEnvio) {
        this.servicioEliminarEnvio = servicioEliminarEnvio;
    }

    public void ejecutar(Long id) { this.servicioEliminarEnvio.ejecutar(id); }
}
