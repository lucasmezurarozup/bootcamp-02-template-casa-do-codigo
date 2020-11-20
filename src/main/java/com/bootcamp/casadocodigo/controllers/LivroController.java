package com.bootcamp.casadocodigo.controllers;

import com.bootcamp.casadocodigo.dtos.CadastrarLivroRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/livro")
public class LivroController {

    @PostMapping("/registrar")
    public ResponseEntity<?> regitrarLivro(@Valid @RequestBody CadastrarLivroRequest cadastrarLivroRequest) {
        return ResponseEntity.ok(null);
    }
}
