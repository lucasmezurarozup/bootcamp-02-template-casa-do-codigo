package com.bootcamp.casadocodigo.categoria;

public class CategoriaDetalhesResponse {

    private String nome;

    public CategoriaDetalhesResponse(String nome) {
        this.nome = nome;
    }

    private CategoriaDetalhesResponse() {

    }

    public String getNome() {
        return nome;
    }
}
