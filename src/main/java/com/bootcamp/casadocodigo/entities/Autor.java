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

    @NotBlank
    @NotNull(message = "Nome é um campo de preenchimento obrigatório")
    private String nome;
    @NotBlank
    @Email
    @NotNull(message = "Email é um campo de preenchimento obrigatório")
    private String email;
    @NotBlank
    @NotNull(message = "Descrição é um campo de preenchimento obrigatório")
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

}
