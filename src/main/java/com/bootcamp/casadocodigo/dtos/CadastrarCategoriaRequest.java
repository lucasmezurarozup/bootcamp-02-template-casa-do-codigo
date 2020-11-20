package com.bootcamp.casadocodigo.dtos;

import com.bootcamp.casadocodigo.entities.Categoria;

import javax.validation.constraints.NotBlank;

public class CadastrarCategoriaRequest {

    @NotBlank(message = "Nome é um campo de preenchimento obrigatório.")
    private String nome;

    public Categoria toObject() {
        return new Categoria(this.nome);
    }

    public String getNome() {
        return nome;
    }
}
