package com.bootcamp.casadocodigo.pagamento;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/compra")
public class CompraController {

    @PostMapping("/nova")
    public ResponseEntity<?> criarCompra(@RequestBody @Valid NovaCompraRequest novaCompraRequest) {
        return null;
    }
}
