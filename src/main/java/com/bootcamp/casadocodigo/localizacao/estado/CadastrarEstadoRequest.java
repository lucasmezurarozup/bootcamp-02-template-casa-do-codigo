package com.bootcamp.casadocodigo.localizacao.estado;

import com.bootcamp.casadocodigo.compartilhado.PaisNotFoundException;
import com.bootcamp.casadocodigo.localizacao.pais.Pais;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

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

        Optional<Pais> pais = Optional.ofNullable(entityManager.find(Pais.class, idPais));

        return new Estado(nome, pais
                .orElseThrow(() ->
                        new PaisNotFoundException("O pais com o id "+idPais+" não está registrado em nosso banco de dados.")));
    }

    public String getNome() {
        return nome;
    }

    public Long getIdPais() {
        return idPais;
    }
}
