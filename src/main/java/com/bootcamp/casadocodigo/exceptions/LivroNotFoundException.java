package com.bootcamp.casadocodigo.exceptions;

public class LivroNotFoundException extends RuntimeException {

    public LivroNotFoundException(String message) {
        super(message);
   }
}
