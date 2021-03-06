package com.bootcamp.casadocodigo.compra;

import com.bootcamp.casadocodigo.compartilhado.Existe;
import com.bootcamp.casadocodigo.compartilhado.PaisNotFoundException;
import com.bootcamp.casadocodigo.cupom.Cupom;
import com.bootcamp.casadocodigo.livro.Livro;
import com.bootcamp.casadocodigo.localizacao.estado.Estado;
import com.bootcamp.casadocodigo.localizacao.estado.EstadoNotFoundException;
import com.bootcamp.casadocodigo.localizacao.pais.Pais;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

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
    @Deprecated
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
                .map(item -> {
                       Livro livro = entityManager.find(Livro.class, item.getIdLivro());
                       Cupom cupomRecebido = pedido.validaPossibilidadeDesconto(entityManager);
                       BigDecimal precoLivro = aplicaPreco(cupomRecebido, livro);
                       BigDecimal quantidade = BigDecimal.valueOf(item.getQuantidade());
                       return precoLivro.multiply(quantidade);
                })
                .reduce(BigDecimal.valueOf(0),
                        ((somatorio, valorTotalItem) ->
                                somatorio.add(valorTotalItem))).setScale(2, RoundingMode.HALF_DOWN);

        BigDecimal precoRecebido = this.pedido.getTotal().setScale(2, RoundingMode.HALF_DOWN);
        System.out.println("precoRecebido: "+precoRecebido +" precoContabilizado: "+precoContabilizado);
        return !precoContabilizado.equals(precoRecebido);
    }

    public BigDecimal aplicaPreco(Cupom cupom, Livro livro) {
        BigDecimal precoLivro;
        if(cupom != null) {
            BigDecimal precoDescontado = cupom.getPercentualDesconto().multiply(livro.getPreco());
            precoLivro = livro.getPreco().subtract(precoDescontado);
        }else {
            precoLivro = livro.getPreco();
        }
        return precoLivro;
    }

    public Compra toObject(EntityManager entityManager) {

        Optional<Pais> pais = Optional.ofNullable(entityManager.find(Pais.class, this.idPais));
        Optional<Estado> estado = Optional.ofNullable(entityManager.find(Estado.class, this.idEstado));

        return new Compra(nome,
                sobrenome,
                email,
                documento,
                endereco,
                complemento,
                cidade,
                pais.orElseThrow(() -> new PaisNotFoundException("o pais com o id "+ idPais+" não foi encontrado em nosso banco de dados.")),
                estado.orElseThrow(() -> new EstadoNotFoundException("o estado com o id "+ idPais+" não foi encontrado em nosso banco de dados.")),
                telefone,
                cep,
                pedido.toObject(entityManager));
    }
}
