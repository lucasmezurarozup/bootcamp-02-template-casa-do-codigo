package com.bootcamp.casadocodigo.compra;

import com.bootcamp.casadocodigo.localizacao.estado.Estado;
import com.bootcamp.casadocodigo.localizacao.pais.Pais;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(
        name = "compras"
)
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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
    private SituacaoCompra situacaoCompra = SituacaoCompra.INICIADA;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Pedido pedido;

    public Pedido getPedido() {
        return pedido;
    }

    public Long getId() {
        return id;
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

    public SituacaoCompra getSituacaoCompra() {
        return situacaoCompra;
    }

    public String getCidade() {
        return cidade;
    }

    public Pais getPais() {
        return pais;
    }

    public Estado getEstado() {
        return estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }

    public Compra(
                  @NotBlank(message = "nome do usuário é um campo de preenchimento obrigatório.") String nome,
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
                  @NotNull Pedido pedido) {


        this.id = id;
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
        this.pedido = pedido;
    }
}
