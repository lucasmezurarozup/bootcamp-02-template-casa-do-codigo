package com.bootcamp.casadocodigo.cupom;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/cupom")
public class CupomController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/novo")
    public ResponseEntity<?> criarCupom(@RequestBody @Valid NovoCupomRequest novoCupomRequest) {
        return ResponseEntity.ok(novoCupomRequest);
    }

}

