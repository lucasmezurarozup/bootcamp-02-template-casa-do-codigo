package com.bootcamp.casadocodigo.autor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @PostMapping("/registrar")
    @Transactional
    public ResponseEntity<?> registrarAutor(@Valid @RequestBody CadastrarAutorRequest cadastrarAutorRequest) {
        Autor autor = autorRepository.save(cadastrarAutorRequest.toObject());
        return ResponseEntity.ok(autor);
    }

    @GetMapping("/listar")
    public ResponseEntity<?> retornaTodosAutores() {
        List<Autor> autores = autorRepository.findAll();
        return ResponseEntity.ok(autores);
    }
}
