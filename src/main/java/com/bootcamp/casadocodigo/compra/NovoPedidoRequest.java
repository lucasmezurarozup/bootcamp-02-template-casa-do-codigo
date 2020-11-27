package com.bootcamp.casadocodigo.compra;

import com.bootcamp.casadocodigo.compartilhado.Existe;
import com.bootcamp.casadocodigo.compartilhado.LivroNotFoundException;
import com.bootcamp.casadocodigo.cupom.Cupom;
import com.bootcamp.casadocodigo.livro.Livro;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
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

    private String cupom;

    @JsonIgnore
    private boolean cupomValido;

    public String getCupom() {
        return cupom;
    }

    public boolean isCupomValido() {
        return cupomValido;
    }

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
                             @Size(min = 1) @Valid @NotNull(message = "a compra deve possuir pelo menos um item.") List<NovaCompraItemRequest> itens,
                             String cupom) {
        this.total = total;
        this.itens = itens;
        this.cupom = cupom;
    }

    public Cupom validaPossibilidadeDesconto(EntityManager entityManager) {
        Cupom desconto = null;

        if(cupom != null) {
            Query query = entityManager.createQuery("SELECT c FROM Cupom c WHERE c.codigo = :codigo");
            query.setParameter("codigo", cupom);
            try {
                desconto = (Cupom) query.getSingleResult();

                if(desconto != null) {
                    if(desconto.getDataValidade().isAfter(LocalDate.now())) {
                        System.out.println("Desconto: codigo (" + desconto.getCodigo() + ") validade: (" + desconto.getDataValidade() + ") " + " percentualDesconto: (" + desconto.getPercentualDesconto() + "))");
                    }else {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cupom "+ cupom + " cadastrado encontra-se expirado.");
                    }
                }
            }catch(NoResultException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cupom "+ cupom + " não encontrado na nossa base de dados.");
            }
        }

        return desconto;
    }

    public Pedido toObject(EntityManager entityManager) {
         List<PedidoItem> pedidoItens = validaExistenciaLivrosSelecionadosCompra(itens, entityManager);
         Cupom cupom = validaPossibilidadeDesconto(entityManager);
         return new Pedido(total, pedidoItens, cupom);
    }

    public List<PedidoItem> validaExistenciaLivrosSelecionadosCompra(List<NovaCompraItemRequest> pedidoItens, EntityManager entityManager) {

        return itens.stream()
                .map(livro -> {
                    Livro livroRecebido = Optional.ofNullable(entityManager.find(Livro.class, livro.getIdLivro()))
                            .orElseThrow(() ->
                                    new LivroNotFoundException("o livro com o id "+livro.getIdLivro()+" não foi encontrado em nosso banco de dados."));
                    return new PedidoItem(livroRecebido, livro.getQuantidade());
                }).collect(Collectors.toList());
    }
}
