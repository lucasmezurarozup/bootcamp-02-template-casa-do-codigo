package com.bootcamp.casadocodigo.compra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.net.URI;

@Validated
@RestController
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    private CompraRepository compraRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @InitBinder
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(
                new VerificacaoDocumentoValidator(),
                new ComparacaoPrecoItensValidator(entityManager));
    }

    @PostMapping("/nova")
    public ResponseEntity<?> criarCompra(@RequestBody @Valid NovaCompraRequest novaCompraRequest, UriComponentsBuilder uriComponentsBuilder) {
        Compra compra = compraRepository.save(novaCompraRequest.toObject(entityManager));
        URI location = uriComponentsBuilder.path("/compra/detalhes/{id}").buildAndExpand(compra.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
