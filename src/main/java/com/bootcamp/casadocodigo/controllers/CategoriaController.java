package com.bootcamp.casadocodigo.controllers;

import com.bootcamp.casadocodigo.dtos.CadastrarCategoriaRequest;
import com.bootcamp.casadocodigo.entities.Categoria;
import com.bootcamp.casadocodigo.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping("/registrar")
    @Transactional
    public ResponseEntity<?> registrarCategoria(@Valid @RequestBody CadastrarCategoriaRequest cadastrarCategoriaRequest) {
        Categoria categoria = categoriaRepository.save(cadastrarCategoriaRequest.toObject());
        return ResponseEntity.ok(categoria);
    }

    @GetMapping("/todas")
    public ResponseEntity<?> listar() {
        List<Categoria> categorias = this.categoriaRepository.findAll();
        return ResponseEntity.ok(categorias);
    }
}
