package com.ceiba.dominio.excepcion;

public class ExcepcionOperacionInvalida extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExcepcionOperacionInvalida(String message) {
        super(message);
    }
}
