package com.bootcamp.casadocodigo.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(
        name = "categorias",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"nome"}, name = "nome_da_categoria")}
)
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Nome é um campo de preenchimento obrigatório.")
    private String nome;

    @Deprecated
    public Categoria() {

    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }
}
