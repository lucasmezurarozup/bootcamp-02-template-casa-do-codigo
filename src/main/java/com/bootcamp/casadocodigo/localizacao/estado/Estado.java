package com.bootcamp.casadocodigo.localizacao.estado;

import com.bootcamp.casadocodigo.localizacao.pais.Pais;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity

@Table(
        name = "estados",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"nomeEstado"}, name = "estado_vinculado_ao_pais")
        }
)
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String nomeEstado;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Pais pais;

    public Estado(@NotBlank String nome,@NotBlank Pais pais) {
        this.nomeEstado = nome;
        this.pais = pais;
    }

    @Deprecated
    private Estado() {

    }

    public String getNome() {
        return nomeEstado;
    }

    public Pais getPais() {
        return pais;
    }
}


