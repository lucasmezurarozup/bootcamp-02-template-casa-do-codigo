package com.bootcamp.casadocodigo.compra;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PedidoDetalhesResponse {
    @NotNull
    private BigDecimal totalSemDesconto;
    @NotNull
    private BigDecimal totalComDesconto;

    @NotNull
    private DescontoResponse descontoResponse;

    @NotNull
    private List<PedidoItemDetalhesResponse> itens = new ArrayList<PedidoItemDetalhesResponse>();

}
