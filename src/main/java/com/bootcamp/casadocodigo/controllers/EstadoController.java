package com.bootcamp.casadocodigo.controllers;

import com.bootcamp.casadocodigo.dtos.CadastrarEstadoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/estado")
public class EstadoController {


    @PostMapping("/pais/{id}")
    public ResponseEntity<?> registrarEstadoAoPais(@RequestBody @Valid CadastrarEstadoRequest cadastrarEstadoRequest) {
        return null;
    }
}
