package com.bootcamp.casadocodigo.dtos;

import com.bootcamp.casadocodigo.entities.Estado;
import com.bootcamp.casadocodigo.entities.Pais;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CadastrarEstadoRequest {

    @NotBlank(message = "Nome é um campo de preenchimento obrigatório.")
    private String nome;

    @NotNull(message = "Pais é um campo de preenchimento obrigatório.")
    private Long idPais;


    public CadastrarEstadoRequest(@NotBlank(message = "Nome é um campo de preenchimento obrigatório.") String nome, @NotBlank(message = "Pais é um campo de preenchimento obrigatório.") Long idPais) {
        this.nome = nome;
        this.idPais = idPais;
    }

    public Estado toObject(EntityManager entityManager) {

        Pais pais = entityManager.find(Pais.class, idPais);

        Assert.notNull(pais, "O pais associado ao estado deve estar previamente registrado.");

        return new Estado(nome, pais);
    }

    public String getNome() {
        return nome;
    }

    public Long getIdPais() {
        return idPais;
    }
}
