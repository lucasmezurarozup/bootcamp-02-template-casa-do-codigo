package com.bootcamp.casadocodigo.compra;

import java.math.BigDecimal;

public class DescontoResponse {
    private boolean existeCupom;
    private BigDecimal valorCupom;

    public DescontoResponse(boolean existeCupom, BigDecimal valorCupom) {
        this.existeCupom = existeCupom;
        this.valorCupom = valorCupom;
    }

    public boolean isExisteCupom() {
        return existeCupom;
    }

    public BigDecimal getValorCupom() {
        return valorCupom;
    }
}
