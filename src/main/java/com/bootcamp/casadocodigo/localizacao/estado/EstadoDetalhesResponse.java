package com.bootcamp.casadocodigo.localizacao.estado;

public class EstadoDetalhesResponse {

    private String estado;
    private String pais;

    public EstadoDetalhesResponse(String estado, String pais) {
        this.estado = estado;
        this.pais = pais;
    }

    public String getEstado() {
        return estado;
    }

    public String getPais() {
        return pais;
    }
}
