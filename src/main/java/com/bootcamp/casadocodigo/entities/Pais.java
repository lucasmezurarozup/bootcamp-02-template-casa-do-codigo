package com.bootcamp.casadocodigo.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(
        name = "paises",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "nomePais", name = "nome_do_pais")
        }
)
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Nome é um campo preenchimento obrigatório.")
    private String nomePais;

    @OneToMany(mappedBy = "pais",cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Estado> estados;

    @Deprecated
    private Pais() {

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nomePais;
    }

    public Pais(String nome) {
        this.nomePais = nome;
    }
}
