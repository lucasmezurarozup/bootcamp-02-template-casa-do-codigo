package com.bootcamp.casadocodigo.pagamento;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonAutoDetect
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
    @NotBlank(message = "complemento do endereço é um campo de preenchimento obrigatório.")
    private String complemento;
    @NotBlank(message = "cidade é um campo de preenchimento obrigatório.")
    private String cidade;
    @NotNull(message = "país é um campo de preenchimento obrigatório.")
    private Long idPais;
    @NotNull(message = "estado é um campo de preenchimento obrigatório.")
    private Long idEstado;
    @NotBlank(message = "telefone é um campo de preenchimento obrigatório.")
    private String telefone;
    private String cep;

    public NovaCompraRequest() {

    }

    public NovaCompraRequest(@NotBlank(message = "nome do usuário é um campo de preenchimento obrigatório.") String nome, @NotBlank(message = "sobrenome do usuário é um campo de preenchimento obrigatório.") String sobrenome, @NotBlank(message = "email do usuário é um campo de preenchimento obrigatório.") String email, @NotBlank(message = "documento do usuário é um campo de preenchimento obrigatório.") String documento, @NotBlank(message = "endereço do usuário é um campo de preenchimento obrigatório.") String endereco, @NotBlank(message = "complemento do endereço é um campo de preenchimento obrigatório.") String complemento, @NotBlank(message = "cidade é um campo de preenchimento obrigatório.") String cidade, @NotNull(message = "país é um campo de preenchimento obrigatório.") Long idPais, @NotNull(message = "estado é um campo de preenchimento obrigatório.") Long idEstado, @NotBlank(message = "telefone é um campo de preenchimento obrigatório.") String telefone, String cep) {
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
    }
}
