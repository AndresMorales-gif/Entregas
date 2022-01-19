package com.ceiba.dominio.excepcion;

public class ExcepcionNoEncontrado extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ExcepcionNoEncontrado(String message) {
        super(message);
    }
}
