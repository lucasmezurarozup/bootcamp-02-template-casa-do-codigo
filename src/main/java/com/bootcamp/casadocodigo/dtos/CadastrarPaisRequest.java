package com.bootcamp.casadocodigo.dtos;

import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

public class CadastrarPaisRequest {

    @NotBlank(message = "Nome é um campo de preenchimento obrigatório.")
    private String nome;

    public CadastrarPaisRequest(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
