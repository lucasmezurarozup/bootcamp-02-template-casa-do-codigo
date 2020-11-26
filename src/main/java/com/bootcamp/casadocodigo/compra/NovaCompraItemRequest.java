package com.bootcamp.casadocodigo.compra;

import com.bootcamp.casadocodigo.compartilhado.Existe;
import com.bootcamp.casadocodigo.livro.Livro;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class NovaCompraItemRequest {
    @NotNull
    @Existe(nomeCampo = "id", entidade = Livro.class)
    private Long idLivro;

    @NotNull
    @Positive
    private int quantidade;

    public void setIdLivro(Long idLivro) {
        this.idLivro = idLivro;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Long getIdLivro() {
        return idLivro;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public NovaCompraItemRequest() {

    }

    public NovaCompraItemRequest(@NotNull Long idLivro, @NotNull int quantidade) {
        this.idLivro = idLivro;
        this.quantidade = quantidade;
    }
}
