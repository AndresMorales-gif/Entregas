package com.ceiba.envio.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.envio.comando.ComandoEnvio;
import com.ceiba.envio.comando.manejador.ManejadorActualizarEnvio;
import com.ceiba.envio.comando.manejador.ManejadorCrearEnvio;
import com.ceiba.envio.comando.manejador.ManejadorEliminarEnvio;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/envios")
@Api(tags = { "Controlador comando envios"})
public class ComandoControladorEnvio {

    private final ManejadorCrearEnvio manejadorCrearEnvio;
    private final ManejadorEliminarEnvio manejadorEliminarEnvio;
    private final ManejadorActualizarEnvio manejadorActualizarEnvio;

    @Autowired
    public ComandoControladorEnvio(ManejadorCrearEnvio manejadorCrearEnvio, ManejadorEliminarEnvio manejadorEliminarEnvio, ManejadorActualizarEnvio manejadorActualizarEnvio) {
        this.manejadorCrearEnvio = manejadorCrearEnvio;
        this.manejadorEliminarEnvio = manejadorEliminarEnvio;
        this.manejadorActualizarEnvio = manejadorActualizarEnvio;
    }


    @PostMapping
    @ApiOperation("Crear Envio")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoEnvio comandoEnvio) {
        return manejadorCrearEnvio.ejecutar(comandoEnvio);
    }

    @DeleteMapping(value="/{id}")
    @ApiOperation("Eliminar Envio")
    public void eliminar(@PathVariable Long id) {
        manejadorEliminarEnvio.ejecutar(id);
    }

    @PutMapping(value="/{id}")
    @ApiOperation("Actualizar Envio")
    public void actualizar(@RequestBody ComandoEnvio comandoEnvio, @PathVariable Long id) {
        comandoEnvio.setId(id);
        this.manejadorActualizarEnvio.ejecutar(comandoEnvio);
    }

}
