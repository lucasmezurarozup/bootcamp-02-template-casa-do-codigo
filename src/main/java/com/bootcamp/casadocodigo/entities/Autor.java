package com.bootcamp.casadocodigo.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "autores", uniqueConstraints = {
                @UniqueConstraint(columnNames = {"email"}, name = "email")
}
)
public class Autor {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Nome é um campo de preenchimento obrigatório")
    private String nome;
    @NotBlank(message = "Email é um campo de preenchimento obrigatório")
    @Email
    private String email;
    @NotBlank(message = "Descrição é um campo de preenchimento obrigatório")
    @Size(max = 400)
    private String descricao;
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Deprecated
    public Autor() {

    }

    public Autor(String nome, String email, String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public LocalDateTime getDataCriacao() {
        return this.dataCriacao;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }
}
