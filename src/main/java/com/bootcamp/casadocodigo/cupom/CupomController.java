package com.bootcamp.casadocodigo.cupom;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CupomRepository cupomRepository;

    @PostMapping("/novo")
    public ResponseEntity<?> criarCupom(@RequestBody @Valid NovoCupomRequest novoCupomRequest) {
        Cupom cupom = cupomRepository.save(novoCupomRequest.toObject(cupomRepository));
        return ResponseEntity.ok(cupom);
    }

}

