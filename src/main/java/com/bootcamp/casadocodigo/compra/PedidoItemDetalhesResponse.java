package com.bootcamp.casadocodigo.compra;

import com.bootcamp.casadocodigo.livro.LivroDetalhesResponse;

import javax.validation.constraints.NotNull;

public class PedidoItemDetalhesResponse {

    @NotNull
    private LivroDetalhesResponse livro;

    @NotNull
    private int quantidade;

    public LivroDetalhesResponse getLivro() {
        return livro;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public PedidoItemDetalhesResponse(PedidoItem pedidoItem) {
        this.livro = new LivroDetalhesResponse(pedidoItem.getLivro());
        this.quantidade = pedidoItem.getQuantidade();
    }
}

