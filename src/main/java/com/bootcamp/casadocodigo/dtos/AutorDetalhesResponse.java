package com.bootcamp.casadocodigo.dtos;

import com.bootcamp.casadocodigo.entities.Autor;

import javax.validation.constraints.NotBlank;

public class AutorDetalhesResponse {

    @NotBlank
    String nome;
    @NotBlank
    String descricao;

    public AutorDetalhesResponse(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
