package com.bootcamp.casadocodigo.pagamento;

import com.bootcamp.casadocodigo.compartilhado.Existe;
import com.bootcamp.casadocodigo.livro.Livro;
import com.bootcamp.casadocodigo.localizacao.estado.Estado;
import com.bootcamp.casadocodigo.localizacao.pais.Pais;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class NovaCompraRequest {

    @NotBlank(message = "nome do usuário é um campo de preenchimento obrigatório.")
    private String nome;
    @NotBlank(message = "sobrenome do usuário é um campo de preenchimento obrigatório.")
    private String sobrenome;
    @NotBlank(message = "email do usuário é um campo de preenchimento obrigatório.")
    private String email;
    @NotBlank(message = "documento do usuário é um campo de preenchimento obrigatório.")
    private String documento;
    @NotBlank(message = "endereço do usuário é um campo de preenchimento obrigatório.")
    private String endereco;

    private String complemento;
    @NotBlank(message = "cidade é um campo de preenchimento obrigatório.")
    private String cidade;
    @NotNull(message = "país é um campo de preenchimento obrigatório.")
    @Existe(nomeCampo = "id", entidade = Pais.class, message = "país não registrado em nossa base de dados.")
    private Long idPais;
    @NotNull(message = "estado é um campo de preenchimento obrigatório.")
    @Existe(nomeCampo = "id", entidade = Estado.class, message = "estado não registrado em nossa base de dados.")
    private Long idEstado;
    @NotBlank(message = "telefone é um campo de preenchimento obrigatório.")
    private String telefone;
    private String cep;

    @Valid
    @NotNull(message = "é necessário possuir um pedido vinculado a compra.")
    private NovoPedidoRequest pedido;

    public NovaCompraRequest() {

    }

    public void setPedido(@NotNull NovoPedidoRequest pedido) {
        this.pedido = pedido;
    }

    public NovoPedidoRequest getPedido() {
        return pedido;
    }

    public NovaCompraRequest(
            @NotBlank(message = "nome do usuário é um campo de preenchimento obrigatório.") String nome,
            @NotBlank(message = "sobrenome do usuário é um campo de preenchimento obrigatório.") String sobrenome,
            @NotBlank(message = "email do usuário é um campo de preenchimento obrigatório.") String email,
            @NotBlank(message = "documento do usuário é um campo de preenchimento obrigatório.") String documento,
            @NotBlank(message = "endereço do usuário é um campo de preenchimento obrigatório.") String endereco,
            String complemento,
            @NotBlank(message = "cidade é um campo de preenchimento obrigatório.") String cidade,
            @NotNull(message = "país é um campo de preenchimento obrigatório.") Long idPais,
            @NotNull(message = "estado é um campo de preenchimento obrigatório.") Long idEstado,
            @NotBlank(message = "telefone é um campo de preenchimento obrigatório.") String telefone,
            String cep,
            NovoPedidoRequest pedido) {

        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.idPais = idPais;
        this.idEstado = idEstado;
        this.telefone = telefone;
        this.cep = cep;
        this.pedido = pedido;
    }

    public boolean validaDocumento() {
        String documento = this.getDocumento();

        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.initialize(null);
        CNPJValidator cnpjValidator = new CNPJValidator();
        cnpjValidator.initialize(null);

        return cpfValidator.isValid(documento, null) || cnpjValidator.isValid(documento, null);
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public Long getIdPais() {
        return idPais;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }

    public boolean comparaPrecoItensComEntrada(EntityManager entityManager) {
        if(pedido.getItens().isEmpty()) return true;

        List<NovaCompraItemRequest> listaItens = pedido.getItens();

        BigDecimal precoContabilizado =  listaItens.stream()
                .map(itemRequest -> {
                       Livro livro = entityManager.find(Livro.class, itemRequest.getIdLivro());
                       BigDecimal precoLivro = livro.getPreco();
                       BigDecimal quantidade = BigDecimal.valueOf(itemRequest.getQuantidade());
                       return precoLivro.multiply(quantidade);
                })
                .reduce(BigDecimal.valueOf(0),
                        ((somatorio, valorTotalItem) ->
                                somatorio.add(valorTotalItem)));

        BigDecimal precoRecebido = this.pedido.getTotal();

        return !precoContabilizado.equals(precoRecebido);
    }
}
