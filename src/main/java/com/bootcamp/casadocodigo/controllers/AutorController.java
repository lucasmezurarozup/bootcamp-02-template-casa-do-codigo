package com.bootcamp.casadocodigo.controllers;

import com.bootcamp.casadocodigo.dtos.CadastrarAutorRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/autor")
public class AutorController {

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarAutor(@Valid @RequestBody CadastrarAutorRequest cadastrarAutorRequest) {
        return null;
    }
}
