package com.bootcamp.casadocodigo.repositories;

import com.bootcamp.casadocodigo.entities.Autor;
import com.bootcamp.casadocodigo.entities.Categoria;
import com.bootcamp.casadocodigo.entities.Livro;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.time.LocalDate;

@DataJpaTest
public class LivroRepositoryTest {

    @Autowired
    private LivroRepository livroRepository;

    private Livro livro;

    private Autor autor;
    private Categoria categoria;

    @BeforeEach
    public void init() {
        autor = new Autor("lucas", "lucas.mezuraro@zup.com.br", "test");
        categoria = new Categoria("Java");
        livro = new Livro("titulo", "resumo", "sumario", BigDecimal.valueOf(29.90), 200, "10910902190", LocalDate.of(2021, 02, 02), categoria, autor);
    }

    @Test
    public void testandoInsercaoLivroComTituloNulo() {
        livro = new Livro(null, "resumo", "sumario", BigDecimal.valueOf(29.90), 200, "10910902190", LocalDate.of(2021, 02, 02), categoria, autor);
       try {
           this.livroRepository.save(livro);
       } catch (Exception exception) {
           Assertions.assertThat(exception).isEqualTo(ConstraintViolationException.class);
       }
    }

    @Test
    public void testandoInsercaoLivroComResumoNulo() {
        livro = new Livro("titulo", null, "sumario", BigDecimal.valueOf(29.90), 200, "10910902190", LocalDate.of(2021, 02, 02), categoria, autor);
        try {
            this.livroRepository.save(livro);
        } catch (Exception exception) {
            Assertions.assertThat(exception).isEqualTo(ConstraintViolationException.class);
        }
    }

    @Test
    public void testandoInsercaoLivroComSumarioNulo() {
        livro = new Livro("titulo", "resumo", null, BigDecimal.valueOf(29.90), 200, "10910902190", LocalDate.of(2021, 02, 02), categoria, autor);
        try {
            this.livroRepository.save(livro);
        } catch (Exception exception) {
            Assertions.assertThat(exception).isEqualTo(ConstraintViolationException.class);
        }
    }

    @Test
    public void testandoInsercaoLivroComPrecoNulo() {
        livro = new Livro("titulo", "resumo", "sumario", null, 200, "10910902190", LocalDate.of(2021, 02, 02), categoria, autor);
        try {
            this.livroRepository.save(livro);
        } catch (Exception exception) {
            Assertions.assertThat(exception).isEqualTo(ConstraintViolationException.class);
        }
    }

    @Test
    public void testandoInsercaoLivroComNumeroPaginasNulo() {
        livro = new Livro("titulo", "resumo", "sumario", BigDecimal.valueOf(29.90), null, "10910902190", LocalDate.of(2021, 02, 02), categoria, autor);
        try {
            this.livroRepository.save(livro);
        } catch (Exception exception) {
            Assertions.assertThat(exception).isEqualTo(ConstraintViolationException.class);
        }
    }

    @Test
    public void testandoInsercaoLivroComIsbnNulo() {
        livro = new Livro("titulo", "resumo", "sumario", BigDecimal.valueOf(29.90), 200, null, LocalDate.of(2021, 02, 02), categoria, autor);
        try {
            this.livroRepository.save(livro);
        } catch (Exception exception) {
            Assertions.assertThat(exception).isEqualTo(ConstraintViolationException.class);
        }
    }

    @Test
    public void testandoInsercaoLivroComDataPublicacaoNulo() {
        livro = new Livro("titulo", "resumo", "sumario", BigDecimal.valueOf(29.90), 200, "10910902190", null, categoria, autor);
        try {
            this.livroRepository.save(livro);
        } catch (Exception exception) {
            Assertions.assertThat(exception).isEqualTo(ConstraintViolationException.class);
        }
    }

    @Test
    public void testandoInsercaoLivroComCategoriaNula() {
        livro = new Livro("titulo", "resumo", "sumario", BigDecimal.valueOf(29.90), 200, "10910902190", LocalDate.of(2021, 02, 02), null, autor);
        try {
            this.livroRepository.save(livro);
        } catch (Exception exception) {
            Assertions.assertThat(exception).isEqualTo(ConstraintViolationException.class);
        }
    }

    @Test
    public void testandoInsercaoLivroComAutorNulo() {
        livro = new Livro("titulo", "resumo", "sumario", BigDecimal.valueOf(29.90), 200, "10910902190", LocalDate.of(2021, 02, 02), categoria, null);
        try {
            this.livroRepository.save(livro);
        } catch (Exception exception) {
            Assertions.assertThat(exception).isEqualTo(ConstraintViolationException.class);
        }
    }
}