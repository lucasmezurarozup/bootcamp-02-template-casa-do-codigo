package com.bootcamp.casadocodigo.livro;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ListarLivrosResponse {
    @NotBlank
    private String titulo;
    @NotNull
    private Long id;

    public ListarLivrosResponse(@NotNull Long id, @NotBlank String titulo) {
        this.titulo = titulo;
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Long getId() {
        return id;
    }
}
