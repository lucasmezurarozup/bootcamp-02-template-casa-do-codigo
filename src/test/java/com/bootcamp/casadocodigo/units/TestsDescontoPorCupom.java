package com.bootcamp.casadocodigo.units;

import com.bootcamp.casadocodigo.autor.Autor;
import com.bootcamp.casadocodigo.categoria.Categoria;
import com.bootcamp.casadocodigo.compra.NovaCompraRequest;
import com.bootcamp.casadocodigo.cupom.Cupom;
import com.bootcamp.casadocodigo.livro.Livro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class TestsDescontoPorCupom {

    @Test
    public void testandoPossibilidadeDescontoCupomNulo() {
        Categoria categoria = new Categoria("Java");
        Autor autor = new Autor("lucas", "lucas.mezuraro@zup.com.br", "test");
        Livro livro = new Livro("titulo", "resumo", "sumario", BigDecimal.valueOf(29.90), 200, "10910902190", LocalDate.of(2021, 02, 02), categoria, autor);
        NovaCompraRequest novaCompraRequest = new NovaCompraRequest();
        BigDecimal precoLivroTest = novaCompraRequest.aplicaPreco(null, livro);

        assertThat(livro.getPreco()).isEqualTo(precoLivroTest);
    }

    @Test
    public void testandoPossibilidadeDescontoCupomExistente() {
        Categoria categoria = new Categoria("Java");
        Autor autor = new Autor("lucas", "lucas.mezuraro@zup.com.br", "test");
        Livro livro = new Livro("titulo", "resumo", "sumario", BigDecimal.valueOf(29.90), 200, "10910902190", LocalDate.of(2021, 02, 02), categoria, autor);
        NovaCompraRequest novaCompraRequest = new NovaCompraRequest();

        Cupom cupom = new Cupom("PROMO15", BigDecimal.valueOf(0.15), LocalDate.of(2020, 12, 31));
        BigDecimal precoLivroTest = novaCompraRequest.aplicaPreco(cupom, livro);
        BigDecimal precoDescontado = livro.getPreco().multiply(cupom.getPercentualDesconto());
        BigDecimal precoLivroAplicadoDesconto = livro.getPreco().subtract(precoDescontado);
        assertThat(precoLivroAplicadoDesconto).isEqualTo(precoLivroTest);
    }
}
