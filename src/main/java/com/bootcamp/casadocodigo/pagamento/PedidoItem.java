package com.bootcamp.casadocodigo.pagamento;

import com.bootcamp.casadocodigo.livro.Livro;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(
        name = "pedido_itens"
)
public class PedidoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    private Livro livro;

    @Positive
    @NotNull
    private int quantidade;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Pedido pedido;

    public PedidoItem(@NotNull Livro livro, @NotNull int quantidade) {
        this.livro = livro;
        this.quantidade = quantidade;
    }

    @Deprecated
    private PedidoItem() {

    }

    public Livro getLivro() {
        return livro;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
