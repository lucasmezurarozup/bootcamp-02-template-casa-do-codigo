package com.bootcamp.casadocodigo.localizacao.pais;

import javax.validation.constraints.NotBlank;

public class CadastrarPaisRequest {

    @NotBlank(message = "Pais é um campo de preenchimento obrigatório.")
    String pais;

    public CadastrarPaisRequest() {

    }

    public CadastrarPaisRequest(String pais) {
        this.pais = pais;
    }
    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Pais toObject() {
        return new Pais(pais);
    }
}
