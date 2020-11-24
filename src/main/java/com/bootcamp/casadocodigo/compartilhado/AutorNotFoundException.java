package com.bootcamp.casadocodigo.compartilhado;

public class AutorNotFoundException extends RuntimeException{

    public AutorNotFoundException(String mensagem) {
        super(mensagem);
    }
}