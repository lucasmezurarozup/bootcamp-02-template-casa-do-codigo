package com.bootcamp.casadocodigo.compartilhado;

public class LivroNotFoundException extends RuntimeException {

    public LivroNotFoundException(String message) {
        super(message);
    }
}
