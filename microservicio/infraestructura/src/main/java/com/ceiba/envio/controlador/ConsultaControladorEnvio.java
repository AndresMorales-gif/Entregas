package com.ceiba.envio.controlador;

import com.ceiba.envio.consulta.ManejadorEnvioPorId;
import com.ceiba.envio.consulta.ManejadorListarEnvios;
import com.ceiba.envio.modelo.dto.DtoEnvio;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/envios")
@Api(tags={"Controlador consulta envios"})
public class ConsultaControladorEnvio {

    private final ManejadorListarEnvios manejadorListarEnvios;
    private final ManejadorEnvioPorId manejadorEnvioPorId;

    public ConsultaControladorEnvio(ManejadorListarEnvios manejadorListarEnvios, ManejadorEnvioPorId manejadorEnvioPorId) {
        this.manejadorListarEnvios = manejadorListarEnvios;
        this.manejadorEnvioPorId = manejadorEnvioPorId;
    }

    @GetMapping(value="/usuario/{remitente}")
    @ApiOperation("Listar Envios por remitente")
    public List<DtoEnvio> listar(@PathVariable Long remitente, @RequestParam(value = "consulta", required = false) String tipoConsulta) {
        return manejadorListarEnvios.ejecutar(remitente, tipoConsulta);
    }

    @GetMapping(value="/{id}")
    @ApiOperation("Encontrar envio por id")
    public DtoEnvio encontrarPorDocumento(@PathVariable Long id) {
        return this.manejadorEnvioPorId.ejecutar(id);
    }

}
