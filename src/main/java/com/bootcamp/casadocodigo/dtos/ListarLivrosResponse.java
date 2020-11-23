package com.bootcamp.casadocodigo.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ListarLivrosResponse {
    @NotBlank
    private String titulo;
    @NotNull
    private Long id;

    public ListarLivrosResponse(@NotBlank String titulo, @NotNull Long id) {
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
