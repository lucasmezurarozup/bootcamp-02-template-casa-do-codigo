package com.bootcamp.casadocodigo.dtos;

import com.bootcamp.casadocodigo.entities.Autor;
import com.bootcamp.casadocodigo.entities.Categoria;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CadastrarLivroRequest {

    @NotBlank(message = "título é um campo de preenchimento obrigatório.")
    private String titulo;
    @NotBlank(message = "resumo é um campo de preenchimento obrigatório.")
    @Size(max = 500)
    private String resumo;
    @NotBlank(message = "sumário é um campo de preenchimento obrigatório.")
    private String sumario;
    @NotNull(message = "preço é um campo de preenchimento obrigatório.")
    @Min(20)
    private BigDecimal preco;
    @NotNull(message = "número de páginas é um campo de preenchimento obrigatório.")
    @Min(100)
    private Integer numeroPaginas;
    @NotBlank(message = "ISBN é um campo de preenchimento obrigatório.")
    private String isbn;

    @Future
    @NotNull(message = "data de publicação é um campo de preenchimento obrigatório.")
    private LocalDate dataPublicacao;

    @NotNull(message = "catégoria é um campo de preenchimento obrigatório.")
    private Categoria categoria;

    @NotNull(message = "autor é um campo de preenchimento obrigatório.")
    private Autor autor;

    public CadastrarLivroRequest(
            String titulo,
            String resumo,
            String sumario,
            BigDecimal preco,
            Integer numeroPaginas,
            String isbn,
            LocalDate dataPublicacao,
            Categoria categoria,
            Autor autor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.categoria = categoria;
        this.autor = autor;
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
}
