package com.usuarios.exception;

public class UsuarioYaRegistradoException extends RuntimeException {

    public UsuarioYaRegistradoException(String mensaje) {
        super(mensaje);
    }
}
