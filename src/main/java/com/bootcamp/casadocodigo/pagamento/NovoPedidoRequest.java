package com.bootcamp.casadocodigo.pagamento;

import com.bootcamp.casadocodigo.compartilhado.LivroNotFoundException;
import com.bootcamp.casadocodigo.livro.Livro;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class NovoPedidoRequest {

    @NotNull(message = "o total do pedido é um campo de preenchimento obrigatório.")
    private BigDecimal total;

    @Size(min = 1)
    @NotNull
    @Valid
    private List<NovaCompraItemRequest> itens = new ArrayList<NovaCompraItemRequest>();

    //private List<NovaCompraItemRequest> itens;

    /*@Size(min = 1)
    //@Valid
    @NotNull(message = "a compra deve possuir pelo menos um item.")

    private List<NovaCompraItemRequest> itens;*/

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTotal() {
        return this.total;
    }

    public void setItens(List<NovaCompraItemRequest> itens) {
        this.itens = itens;
    }

    public List<NovaCompraItemRequest> getItens() {
        return itens;
    }

    public NovoPedidoRequest() {

    }

    public NovoPedidoRequest(@Positive @NotNull(message = "o total do pedido é um campo de preenchimento obrigatório.") BigDecimal total,
                             @Size(min = 1) @Valid @NotNull(message = "a compra deve possuir pelo menos um item.") List<NovaCompraItemRequest> itens) {
        this.total = total;
        this.itens = itens;
    }

    public Pedido toObject(EntityManager entityManager) {

         List<PedidoItem> pedidoItens = itens.stream()
                 .map(livro -> {
                     Livro livroRecebido = Optional.ofNullable(entityManager.find(Livro.class, livro.getIdLivro()))
                             .orElseThrow(() ->
                                     new LivroNotFoundException("o livro com o id "+livro.getIdLivro()+" não foi encontrado em nosso banco de dados."));
                     return new PedidoItem(livroRecebido, livro.getQuantidade());
                 }).collect(Collectors.toList());

         return new Pedido(total, pedidoItens);


    }
}
