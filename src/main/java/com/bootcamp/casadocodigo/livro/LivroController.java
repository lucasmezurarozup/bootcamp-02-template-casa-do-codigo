package com.bootcamp.casadocodigo.livro;

import com.bootcamp.casadocodigo.autor.AutorDetalhesResponse;
import com.bootcamp.casadocodigo.categoria.CategoriaDetalhesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/listar")
    @ResponseBody
    public ResponseEntity<?> listarLivros() {
        List<ListarLivrosResponse> livros = livroRepository.findAll()
                .stream()
                .map(livro -> new ListarLivrosResponse(livro.getId(),livro.getTitulo()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/detalhes/{id}")
    public ResponseEntity<?> detalhesLivro(@PathVariable("id") Long id) {
        Livro livro = livroRepository.findById(id).orElseThrow(() -> new LivroNotFoundException("O livro com o id: "+id+" não existe em nossa base de dados"));
        LivroDetalhesResponse livroDetalhesResponse = new LivroDetalhesResponse(livro.getTitulo(),
                livro.getResumo(),
                livro.getSumario(),
                livro.getPreco(),
                livro.getNumeroPaginas(),
                livro.getIsbn(),
                new CategoriaDetalhesResponse(livro.getCategoria().getNome()),
                new AutorDetalhesResponse(livro.getAutor().getNome(), livro.getAutor().getDescricao()));
        return ResponseEntity.ok(livroDetalhesResponse);
    }
}
