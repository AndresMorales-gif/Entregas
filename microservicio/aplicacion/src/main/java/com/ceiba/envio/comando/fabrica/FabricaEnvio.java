package com.ceiba.envio.comando.fabrica;

import com.ceiba.envio.comando.ComandoEnvio;
import com.ceiba.envio.modelo.entidad.Envio;
import org.springframework.stereotype.Component;

@Component
public class FabricaEnvio {

    public Envio crear(ComandoEnvio comandoEnvio) {
        return new Envio(comandoEnvio.getId(),
                comandoEnvio.getRemitente(),
                comandoEnvio.getDestinatario(),
                comandoEnvio.getZona(),
                comandoEnvio.getEnvioPlus(),
                comandoEnvio.getPesoCarga());
    }
}
