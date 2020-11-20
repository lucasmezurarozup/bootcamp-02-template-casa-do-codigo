package com.bootcamp.casadocodigo.controllers;

import com.bootcamp.casadocodigo.dtos.CadastrarLivroRequest;
import com.bootcamp.casadocodigo.entities.Livro;
import com.bootcamp.casadocodigo.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/registrar")
    @Transactional
    public ResponseEntity<?> regitrarLivro(@RequestBody @Valid CadastrarLivroRequest cadastrarLivroRequest) {
        Livro livro = livroRepository.save(cadastrarLivroRequest.toObject(entityManager));
        return ResponseEntity.ok(livro);
    }
}
