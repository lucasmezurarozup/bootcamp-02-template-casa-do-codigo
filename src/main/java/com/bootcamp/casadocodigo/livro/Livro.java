package com.bootcamp.casadocodigo.livro;

import com.bootcamp.casadocodigo.autor.Autor;
import com.bootcamp.casadocodigo.categoria.Categoria;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(
        name = "livros",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"titulo"}, name = "titulo"),
                @UniqueConstraint(columnNames = {"isbn"}, name = "isbn")
        }
)
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataPublicacao;

    @NotNull(message = "catégoria é um campo de preenchimento obrigatório.")
    @ManyToOne
    private Categoria categoria;

    @NotNull(message = "autor é um campo de preenchimento obrigatório.")
    @ManyToOne
    private Autor autor;

    public Livro(String titulo, String resumo, String sumario, BigDecimal preco, Integer numeroPaginas, String isbn, LocalDate dataPublicacao, Categoria categoria, Autor autor) {
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

    @Deprecated
    private Livro() {

    }

    public Long getId() {
        return id;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public Autor getAutor() {
        return autor;
    }
}
