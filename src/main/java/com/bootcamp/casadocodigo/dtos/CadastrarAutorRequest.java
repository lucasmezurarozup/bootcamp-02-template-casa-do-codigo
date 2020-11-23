package com.bootcamp.casadocodigo.dtos;

import com.bootcamp.casadocodigo.entities.Autor;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import javax.validation.constraints.*;
import java.io.Serializable;

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
