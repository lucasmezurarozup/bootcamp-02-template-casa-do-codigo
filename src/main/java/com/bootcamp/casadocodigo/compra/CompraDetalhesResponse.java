package com.bootcamp.casadocodigo.compra;

import com.bootcamp.casadocodigo.localizacao.estado.Estado;
import com.bootcamp.casadocodigo.localizacao.estado.EstadoDetalhesResponse;
import com.bootcamp.casadocodigo.localizacao.pais.Pais;
import com.bootcamp.casadocodigo.localizacao.pais.PaisDetalhesResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CompraDetalhesResponse {

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
    @ManyToOne(fetch = FetchType.LAZY)
    private PaisDetalhesResponse pais;
    @NotNull(message = "estado é um campo de preenchimento obrigatório.")
    @ManyToOne(fetch = FetchType.LAZY)
    private EstadoDetalhesResponse estado;
    @NotBlank(message = "telefone é um campo de preenchimento obrigatório.")
    private String telefone;
    private String cep;
    @Enumerated(EnumType.STRING)
    private SituacaoCompra situacaoCompra;

    @NotNull
    private PedidoDetalhesResponse pedido;

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

    public PaisDetalhesResponse getPais() {
        return pais;
    }

    public EstadoDetalhesResponse getEstado() {
        return estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }

    public SituacaoCompra getSituacaoCompra() {
        return situacaoCompra;
    }

    public PedidoDetalhesResponse getPedido() {
        return pedido;
    }

    public CompraDetalhesResponse(Compra compra) {
        this.nome = compra.getNome();
        this.sobrenome = compra.getSobrenome();
        this.email = compra.getEmail();
        this.documento = compra.getDocumento();
        this.endereco = compra.getEndereco();
        this.complemento = compra.getComplemento();
        this.cidade = compra.getCidade();
        this.pais = new PaisDetalhesResponse(compra.getPais().getNome());
        this.estado = new EstadoDetalhesResponse(compra.getEstado().getNome(), compra.getPais().getNome());
        this.telefone = compra.getTelefone();
        this.cep = compra.getCep();
        this.situacaoCompra = compra.getSituacaoCompra();
        this.pedido = new PedidoDetalhesResponse(compra.getPedido());
    }
}
