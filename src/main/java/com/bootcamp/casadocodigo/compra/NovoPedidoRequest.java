package com.bootcamp.casadocodigo.compra;

import com.bootcamp.casadocodigo.compartilhado.Existe;
import com.bootcamp.casadocodigo.compartilhado.LivroNotFoundException;
import com.bootcamp.casadocodigo.cupom.Cupom;
import com.bootcamp.casadocodigo.livro.Livro;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
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

    public Pedido toObject(EntityManager entityManager) {

        Optional<Cupom> desconto;

        if(cupom != null) {
            Query query = entityManager.createQuery("SELECT c FROM Cupom c WHERE c.codigo = :codigo");
            query.setParameter("codigo", cupom);
            try {
                desconto = Optional.ofNullable((Cupom) query.getSingleResult());

                desconto.ifPresent((cupomExistente) -> {
                      if(cupomExistente.getDataValidade().isAfter(LocalDate.now())) {
                           System.out.println("Desconto: codigo (" + cupomExistente.getCodigo() + ") validade: (" + cupomExistente.getDataValidade() + ") " + " percentualDesconto: (" + cupomExistente.getPercentualDesconto() + "))");
                      }else {
                           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cupom "+ cupom + " cadastrado encontra-se expirado.");
                      }
                });
            }catch(NoResultException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cupom "+ cupom + " não encontrado na nossa base de dados.");
            }
        }

         List<PedidoItem> pedidoItens = validaExistenciaLivrosSelecionadosCompra(itens, entityManager);

         return new Pedido(total, pedidoItens);
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
