package com.bootcamp.casadocodigo.controllers;

import com.bootcamp.casadocodigo.dtos.CadastrarCategoriaRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarCategoria(@Valid @RequestBody CadastrarCategoriaRequest cadastrarCategoriaRequest) {
        return ResponseEntity.ok(null);
    }
}
