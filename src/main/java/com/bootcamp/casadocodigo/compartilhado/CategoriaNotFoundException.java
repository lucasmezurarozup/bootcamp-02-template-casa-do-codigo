package com.bootcamp.casadocodigo.compartilhado;

public class CategoriaNotFoundException extends RuntimeException{

    public CategoriaNotFoundException(String mensagem) {
        super(mensagem);
    }
}
