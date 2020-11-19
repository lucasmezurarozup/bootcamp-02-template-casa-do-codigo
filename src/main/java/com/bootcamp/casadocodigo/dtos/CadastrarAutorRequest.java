package com.bootcamp.casadocodigo.dtos;

import com.bootcamp.casadocodigo.entities.Autor;

import javax.validation.constraints.NotNull;

public class CadastrarAutorRequest {
    @NotNull(message = "Nome é um campo de preenchimento obrigatório")
    String nome;
    @NotNull(message = "Email é um campo de preenchimento obrigatório")
    String email;
    @NotNull(message = "Descrição é um campo de preenchimento obrigatório")
    String descricao;

    public Autor toObject() {
        return new Autor(this.nome, this.email, this.descricao);
    }
}
