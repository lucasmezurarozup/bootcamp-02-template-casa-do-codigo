package com.bootcamp.casadocodigo.dtos;

import com.bootcamp.casadocodigo.entities.Autor;
import com.bootcamp.casadocodigo.entities.Categoria;
import com.bootcamp.casadocodigo.entities.Livro;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CadastrarLivroRequest {

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

    @Future
    @NotNull(message = "data de publicação é um campo de preenchimento obrigatório.")
    private LocalDate dataPublicacao;

    @NotNull(message = "catégoria é um campo de preenchimento obrigatório.")
    private Long idCategoria;

    @NotNull(message = "autor é um campo de preenchimento obrigatório.")
    private Long idAutor;

    public CadastrarLivroRequest(
            String titulo,
            String resumo,
            String sumario,
            BigDecimal preco,
            Integer numeroPaginas,
            String isbn,
            LocalDate dataPublicacao,
            Long idCategoria,
            Long idAutor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.idCategoria = idCategoria;
        this.idAutor = idAutor;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
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

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public Livro toObject(EntityManager entityManager) {

        Categoria categoria = entityManager.find(Categoria.class, this.idCategoria);
        Autor autor = entityManager.find(Autor.class, this.idAutor);

        Assert.notNull(categoria, "categoria é um campo obrigatório.");
        Assert.notNull(autor, "autor é um campo obrigatório.");

        return new Livro(this.titulo, this.resumo, this.sumario, this.preco, this.numeroPaginas, this.isbn, this.dataPublicacao, categoria, autor);
    }
}
