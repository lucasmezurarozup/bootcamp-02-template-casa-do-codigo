package com.bootcamp.casadocodigo.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(
        name = "paises",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "nome", name = "nome_do_pais")
        }
)
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Nome é um campo preenchimento obrigatório.")
    private String nome;

    @Deprecated
    private Pais() {

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Pais(@NotBlank(message = "Nome é um campo preenchimento obrigatório.") String nome) {
        this.nome = nome;
    }
}
