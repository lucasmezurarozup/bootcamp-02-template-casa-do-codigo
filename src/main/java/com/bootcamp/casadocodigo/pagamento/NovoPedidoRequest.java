package com.bootcamp.casadocodigo.pagamento;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
    public BigDecimal getTotal() {
        return total;
    }

    public void setItens(List<NovaCompraItemRequest> itens) {
        this.itens = itens;
    }

    public List<NovaCompraItemRequest> getItens() {
        return itens;
    }

    public NovoPedidoRequest() {

    }

    public NovoPedidoRequest(BigDecimal total) {
        this.total = total;
    }

    public NovoPedidoRequest(@Positive @NotNull(message = "o total do pedido é um campo de preenchimento obrigatório.") BigDecimal total,
                             @Size(min = 1) @Valid @NotNull(message = "a compra deve possuir pelo menos um item.") List<NovaCompraItemRequest> itens) {
        this.total = total;
        this.itens = itens;
    }
}
