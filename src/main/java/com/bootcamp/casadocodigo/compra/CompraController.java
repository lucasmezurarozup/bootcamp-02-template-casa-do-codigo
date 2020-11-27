package com.bootcamp.casadocodigo.compra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

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

    @GetMapping("/detalhes/{id}")
    public ResponseEntity<?> detalhesCompra(@PathVariable("id") Long id) {
        System.out.println("Long: "+ id);
        Compra compra = compraRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "compra com o id: "+id+" não foi encontrada em nosso banco de dados."));
        CompraDetalhesResponse compraDetalhesResponse = new CompraDetalhesResponse(compra);
        return ResponseEntity.ok(compraDetalhesResponse);

    }
}
