package com.ceiba.zona.controlador;

import com.ceiba.zona.consulta.ManejadorListarZonas;
import com.ceiba.zona.modelo.dto.DtoZona;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/zonas")
@Api(tags={"Controlador consulta zona"})
public class ConsultaControladorZona {

    private final ManejadorListarZonas manejadorListarZonas;

    public ConsultaControladorZona(ManejadorListarZonas manejadorListarZonas) {
        this.manejadorListarZonas = manejadorListarZonas;
    }

    @GetMapping
    @ApiOperation("Listar zonas")
    public List<DtoZona> listar() {return this.manejadorListarZonas.ejecutar();}
}
