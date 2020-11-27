package com.bootcamp.casadocodigo.compra;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.PreUpdate;

public class AlteracaoCupomListener {

    @PreUpdate
    public void verificaAlteracaoNoCupomRegistrado(final Pedido pedido) {
       if (pedido.getCupom() != null) {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "o cupom não deve ser alterado.");
       }

    }
}
