package com.bootcamp.casadocodigo.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "Descrição é um campo de preenchimento obrigatório")
    private String descricao;
    @NotBlank(message = "Email é um campo de preenchimento obrigatório")
    private String email;
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
