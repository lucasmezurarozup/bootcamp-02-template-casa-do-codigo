package com.bootcamp.casadocodigo.controllers;

import com.bootcamp.casadocodigo.dtos.CadastrarPaisRequest;
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

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarPais(@RequestBody @Valid CadastrarPaisRequest cadastrarPaisRequest) {
        return null;
    }
}
