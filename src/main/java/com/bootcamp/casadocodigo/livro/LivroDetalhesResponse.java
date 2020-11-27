package com.bootcamp.casadocodigo.livro;

import com.bootcamp.casadocodigo.autor.AutorDetalhesResponse;
import com.bootcamp.casadocodigo.categoria.CategoriaDetalhesResponse;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class LivroDetalhesResponse {

    @NotBlank(message = "título é um campo de preenchimento obrigatório.")
    private String titulo;
    @NotBlank(message = "resumo é um campo de preenchimento obrigatório.")
    @Size(max = 500)
    private String resumo;
    @NotBlank(message = "sumário é um campo de preenchimento obrigatório.")
    private String sumario;
    @NotNull(message = "preço é um campo de preenchimento obrigatório.")
    @DecimalMin(value = "20", message = "o preço deve ser maior que 20 reais.")
    private BigDecimal preco;
    @NotNull(message = "número de páginas é um campo de preenchimento obrigatório.")
    @Min(value = 100, message = "O livro deve ter mais de 100 páginas.")
    private Integer numeroPaginas;
    @NotBlank(message = "ISBN é um campo de preenchimento obrigatório.")
    private String isbn;

    @NotNull(message = "catégoria é um campo de preenchimento obrigatório.")
    private CategoriaDetalhesResponse categoria;

    @NotNull(message = "autor é um campo de preenchimento obrigatório.")
    private AutorDetalhesResponse autor;

    public LivroDetalhesResponse(
            @NotBlank(message = "título é um campo de preenchimento obrigatório.") String titulo,
            @NotBlank(message = "resumo é um campo de preenchimento obrigatório.") @Size(max = 500) String resumo,
            @NotBlank(message = "sumário é um campo de preenchimento obrigatório.") String sumario,
            @NotNull(message = "preço é um campo de preenchimento obrigatório.") @DecimalMin(value = "20", message = "o preço deve ser maior que 20 reais.") BigDecimal preco,
            @NotNull(message = "número de páginas é um campo de preenchimento obrigatório.") @Min(value = 100, message = "O livro deve ter mais de 100 páginas.") Integer numeroPaginas,
            @NotBlank(message = "ISBN é um campo de preenchimento obrigatório.") String isbn,
            CategoriaDetalhesResponse categoria,
            AutorDetalhesResponse autor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.categoria = categoria;
        this.autor = autor;
    }

    public LivroDetalhesResponse(Livro livro) {
        this.titulo = livro.getTitulo();
        this.autor = new AutorDetalhesResponse(livro.getAutor().getNome(), livro.getAutor().getDescricao());
        this.categoria = new CategoriaDetalhesResponse(livro.getCategoria().getNome());
        this.resumo = livro.getResumo();
        this.sumario = livro.getSumario();
        this.numeroPaginas = livro.getNumeroPaginas();
        this.isbn = livro.getIsbn();
        this.preco = livro.getPreco();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getNumeroPaginas() {
        return numeroPaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public AutorDetalhesResponse getAutor() {
        return autor;
    }

    public CategoriaDetalhesResponse getCategoria() {
        return categoria;
    }
}
