package com.bootcamp.casadocodigo.controllers;

import com.bootcamp.casadocodigo.dtos.CadastrarPaisRequest;
import com.bootcamp.casadocodigo.entities.Pais;
import com.bootcamp.casadocodigo.repositories.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/pais")
public class PaisController {

    @Autowired
    PaisRepository paisRepository;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarPais (@RequestBody @Valid CadastrarPaisRequest cadastrarPaisRequest) {
        Pais pais = paisRepository.save(cadastrarPaisRequest.toObject());
        return ResponseEntity.ok(pais);
    }
}
