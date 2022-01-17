package com.ceiba.usuario.modelo.entidad;


import lombok.Getter;

import java.time.LocalDateTime;

import static com.ceiba.dominio.ValidadorArgumento.validarLongitud;
import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
public class Usuario {

    private static final String EL_DOCUMENTO_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A = "El número de documento debe tener una longitud mayor o igual a %s";
    private static final String SE_DEBE_INGRESAR_EL_DOCUMENTO = "Se debe ingresar el número de documento";
    private static final String SE_DEBE_INGRESAR_EL_NOMBRE_DE_USUARIO = "Se debe ingresar el nombre de usuario";

    private static final int LONGITUD_MINIMA_DOCUMENTO = 6;

    private Long id;
    private String nombre;
    private String idDocumento;
    private LocalDateTime fechaCreacion;

    public Usuario(Long id,String nombre, String idDocumento) {
        validarObligatorio(nombre, SE_DEBE_INGRESAR_EL_NOMBRE_DE_USUARIO);
        validarObligatorio(idDocumento, SE_DEBE_INGRESAR_EL_DOCUMENTO);
        validarLongitud(idDocumento, LONGITUD_MINIMA_DOCUMENTO, String.format(EL_DOCUMENTO_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A,LONGITUD_MINIMA_DOCUMENTO));

        this.id = id;
        this.nombre = nombre;
        this.idDocumento = idDocumento;
        this.fechaCreacion = LocalDateTime.now();
    }

}
