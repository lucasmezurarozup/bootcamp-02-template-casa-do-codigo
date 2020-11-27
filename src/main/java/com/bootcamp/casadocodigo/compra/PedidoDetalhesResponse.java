package com.bootcamp.casadocodigo.compra;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoDetalhesResponse {
    @NotNull
    private BigDecimal totalSemDesconto;
    @NotNull
    private BigDecimal totalComDesconto;

    @NotNull
    private DescontoResponse desconto;
;
    @NotNull
    private List<PedidoItemDetalhesResponse> itens = new ArrayList<PedidoItemDetalhesResponse>();


    public BigDecimal getTotalSemDesconto() {
        return totalSemDesconto;
    }

    public BigDecimal getTotalComDesconto() {
        return totalComDesconto;
    }

    public DescontoResponse getDesconto() {
        return desconto;
    }

    public List<PedidoItemDetalhesResponse> getItens() {
        return itens;
    }

    @Deprecated
    public PedidoDetalhesResponse() {

    }

    public PedidoDetalhesResponse(Pedido pedido) {
        this.totalSemDesconto = pedido.getItens().stream().map(pedidoItem -> {
           return pedidoItem.getLivro().getPreco().multiply(BigDecimal.valueOf(pedidoItem.getQuantidade()));
        }).reduce(BigDecimal.ZERO,
                (subTotal, precoItem) -> subTotal.add(precoItem));
        this.totalComDesconto = pedido.getTotal();
        if (pedido.getCupom() != null) {
            this.desconto = new DescontoResponse(true, pedido.getCupom().getPercentualDesconto());
        }else {
            this.desconto = new DescontoResponse(false, null);
        }
        this.itens = pedido.getItens().stream().map(pedidoItem -> new PedidoItemDetalhesResponse(pedidoItem)).collect(Collectors.toList());
    }
}
