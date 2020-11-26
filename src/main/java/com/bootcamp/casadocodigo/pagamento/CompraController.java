package com.bootcamp.casadocodigo.pagamento;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/compra")
public class CompraController {

    @PersistenceContext
    private EntityManager entityManager;

    @InitBinder
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(
                new VerificacaoDocumentoValidator(),
                new ComparacaoPrecoItensValidator(entityManager));
    }

    @PostMapping("/nova")
    public ResponseEntity<?> criarCompra(@RequestBody @Valid NovaCompraRequest novaCompraRequest) {
        return ResponseEntity.ok(novaCompraRequest);
    }
}
