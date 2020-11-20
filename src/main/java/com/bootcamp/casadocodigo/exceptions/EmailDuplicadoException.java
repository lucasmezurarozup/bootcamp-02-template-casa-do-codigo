package com.bootcamp.casadocodigo.exceptions;

public class EmailDuplicadoException extends RuntimeException {

    public EmailDuplicadoException(String mensagem) {
        super(mensagem);
    }
}
