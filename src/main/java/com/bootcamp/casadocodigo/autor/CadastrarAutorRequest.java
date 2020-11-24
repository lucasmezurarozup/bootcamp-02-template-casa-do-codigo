package com.bootcamp.casadocodigo.autor;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.validation.constraints.*;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CadastrarAutorRequest {
    @NotBlank(message = "Nome é um campo de preenchimento obrigatório")
    private String nome;
    @NotBlank(message = "Email é um campo de preenchimento obrigatório")
    @Email
    private String email;
    @NotBlank(message = "Descrição é um campo de preenchimento obrigatório")
    @Size(max = 400)
    private String descricao;

    public CadastrarAutorRequest(String nome, String email, String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    @Deprecated
    public CadastrarAutorRequest() {

    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDescricao() {
        return descricao;
    }

    public Autor toObject() {
        return new Autor(this.nome, this.email, this.descricao);
    }
}
