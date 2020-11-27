package com.bootcamp.casadocodigo.compra;

import com.bootcamp.casadocodigo.cupom.Cupom;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "pedidos")
@EntityListeners(AlteracaoCupomListener.class)
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "o total do pedido é um campo de preenchimento obrigatório.")
    private BigDecimal total;

    @Size(min = 1)
    @NotNull
    @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY)
    private List<PedidoItem> itens;

    @OneToOne(fetch = FetchType.LAZY)
    private Cupom cupom;

    public Cupom getCupom() {
        return cupom;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<PedidoItem> getItens() {
        return itens;
    }

    @Deprecated
    private Pedido() {

    }

    public Pedido(@NotNull(message = "o total do pedido é um campo de preenchimento obrigatório.") BigDecimal total,
                  @Size(min = 1) @NotNull List<PedidoItem> itens, Cupom cupom) {
        this.total = total;
        this.itens = itens;
        this.cupom = cupom;
    }
}
