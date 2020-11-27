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
    private DescontoResponse descontoResponse;
;
    @NotNull
    private List<PedidoItemDetalhesResponse> itens = new ArrayList<PedidoItemDetalhesResponse>();


    public BigDecimal getTotalSemDesconto() {
        return totalSemDesconto;
    }

    public BigDecimal getTotalComDesconto() {
        return totalComDesconto;
    }

    public DescontoResponse getDescontoResponse() {
        return descontoResponse;
    }

    public List<PedidoItemDetalhesResponse> getItens() {
        return itens;
    }

    @Deprecated
    public PedidoDetalhesResponse() {

    }

    public PedidoDetalhesResponse(Pedido pedido) {
        this.totalComDesconto = pedido.getTotal();
        if (pedido.getCupom() != null) {
            this.descontoResponse = new DescontoResponse(true, pedido.getCupom().getPercentualDesconto());
        }else {
            this.descontoResponse = new DescontoResponse(false, null);
        }
        this.itens = pedido.getItens().stream().map(pedidoItem -> new PedidoItemDetalhesResponse(pedidoItem)).collect(Collectors.toList());
    }
}
