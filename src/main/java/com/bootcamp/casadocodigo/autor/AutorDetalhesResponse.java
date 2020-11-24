package com.bootcamp.casadocodigo.autor;

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
