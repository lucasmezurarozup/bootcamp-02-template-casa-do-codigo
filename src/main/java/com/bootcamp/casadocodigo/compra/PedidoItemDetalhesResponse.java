package com.bootcamp.casadocodigo.compra;

import com.bootcamp.casadocodigo.livro.LivroDetalhesResponse;

import javax.validation.constraints.NotNull;

public class PedidoItemDetalhesResponse {

    @NotNull
    private LivroDetalhesResponse livro;

    @NotNull
    private int quantidade;
}

