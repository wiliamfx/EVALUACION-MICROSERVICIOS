package com.usuarios.exception;

public class RolNoExisteException extends RuntimeException {

    public RolNoExisteException(String mensaje) {
        super(mensaje);
    }
}
