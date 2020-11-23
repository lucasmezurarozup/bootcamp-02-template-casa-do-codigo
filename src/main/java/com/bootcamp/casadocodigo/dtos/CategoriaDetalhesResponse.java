package com.bootcamp.casadocodigo.dtos;

import com.bootcamp.casadocodigo.entities.Categoria;

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
