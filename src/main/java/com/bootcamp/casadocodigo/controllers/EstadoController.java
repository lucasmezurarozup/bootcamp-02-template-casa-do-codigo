package com.bootcamp.casadocodigo.controllers;

import com.bootcamp.casadocodigo.dtos.CadastrarEstadoRequest;
import com.bootcamp.casadocodigo.entities.Estado;
import com.bootcamp.casadocodigo.exceptions.PaisNaoRegistradoException;
import com.bootcamp.casadocodigo.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/estado")
public class EstadoController {

    @Autowired
    EstadoRepository estadoRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/pais/{id}")
    @Transactional(rollbackOn = PaisNaoRegistradoException.class)
    public ResponseEntity<?> registrarEstadoAoPais(@RequestBody @Valid CadastrarEstadoRequest cadastrarEstadoRequest) {
        Estado estado = cadastrarEstadoRequest.toObject(entityManager);
        estadoRepository
                .save(estado);
        return ResponseEntity.ok().build();
    }
}
