package com.bootcamp.casadocodigo.compra;

import com.bootcamp.casadocodigo.localizacao.estado.Estado;
import com.bootcamp.casadocodigo.localizacao.pais.Pais;

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
    private Pais pais;
    @NotNull(message = "estado é um campo de preenchimento obrigatório.")
    @ManyToOne(fetch = FetchType.LAZY)
    private Estado estado;
    @NotBlank(message = "telefone é um campo de preenchimento obrigatório.")
    private String telefone;
    private String cep;
    @Enumerated(EnumType.STRING)
    private SituacaoCompra situacaoCompra;
    @NotNull
    private PedidoDetalhesResponse pedido;

    public CompraDetalhesResponse(@NotBlank(message = "nome do usuário é um campo de preenchimento obrigatório.") String nome,
                                  @NotBlank(message = "sobrenome do usuário é um campo de preenchimento obrigatório.") String sobrenome,
                                  @NotBlank(message = "email do usuário é um campo de preenchimento obrigatório.") String email,
                                  @NotBlank(message = "documento do usuário é um campo de preenchimento obrigatório.") String documento,
                                  @NotBlank(message = "endereço do usuário é um campo de preenchimento obrigatório.") String endereco,
                                  String complemento,
                                  @NotBlank(message = "cidade é um campo de preenchimento obrigatório.") String cidade,
                                  @NotNull(message = "país é um campo de preenchimento obrigatório.") Pais pais,
                                  @NotNull(message = "estado é um campo de preenchimento obrigatório.") Estado estado,
                                  @NotBlank(message = "telefone é um campo de preenchimento obrigatório.") String telefone,
                                  String cep,
                                  SituacaoCompra situacaoCompra,
                                  @NotNull PedidoDetalhesResponse pedido) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.pais = pais;
        this.estado = estado;
        this.telefone = telefone;
        this.cep = cep;
        this.situacaoCompra = situacaoCompra;
        this.pedido = pedido;
    }
}
