package com.ceiba.usuario.controlador;

import java.util.List;

import com.ceiba.usuario.consulta.ManejadorListarUsuarios;
import com.ceiba.usuario.consulta.ManejadorUsuarioPorDocumento;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.usuario.modelo.dto.DtoUsuario;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/usuarios")
@Api(tags={"Controlador consulta usuario"})
public class ConsultaControladorUsuario {

    private final ManejadorListarUsuarios manejadorListarUsuarios;
    private final ManejadorUsuarioPorDocumento manejadorUsuarioPorDocumento;

    public ConsultaControladorUsuario(ManejadorListarUsuarios manejadorListarUsuarios,
                                      ManejadorUsuarioPorDocumento manejadorUsuarioPorDocumento) {
        this.manejadorListarUsuarios = manejadorListarUsuarios;
        this.manejadorUsuarioPorDocumento = manejadorUsuarioPorDocumento;
    }

    @GetMapping
    @ApiOperation("Listar Usuarios")
    public List<DtoUsuario> listar() {
        return this.manejadorListarUsuarios.ejecutar();
    }

    @GetMapping(value="/{idDocument}")
    @ApiOperation("encontrar Usuario por documento")
    public DtoUsuario encontrarPorDocumento(@PathVariable String idDocument) {
        return this.manejadorUsuarioPorDocumento.ejecutar(idDocument);
    }

}
