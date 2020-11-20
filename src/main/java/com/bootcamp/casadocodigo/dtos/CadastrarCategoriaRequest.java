package com.bootcamp.casadocodigo.dtos;

import javax.validation.constraints.NotBlank;

public class CadastrarCategoriaRequest {

    @NotBlank(message = "Nome é um campo de preenchimento obrigatório.")
    private String nome;
}
