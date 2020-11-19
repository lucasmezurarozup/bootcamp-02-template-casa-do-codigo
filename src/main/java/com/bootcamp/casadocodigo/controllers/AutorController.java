package com.bootcamp.casadocodigo.controllers;

import com.bootcamp.casadocodigo.dtos.CadastrarAutorRequest;
import com.bootcamp.casadocodigo.entities.Autor;
import com.bootcamp.casadocodigo.repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarAutor(@Valid @RequestBody CadastrarAutorRequest cadastrarAutorRequest) {
        Autor autor = autorRepository.save(cadastrarAutorRequest.toObject());
        return ResponseEntity.ok(autor);
    }
}
