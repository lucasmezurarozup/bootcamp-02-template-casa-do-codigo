package com.bootcamp.casadocodigo.repositories;

import com.bootcamp.casadocodigo.entities.Categoria;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;

@DataJpaTest
public class CategoriaRepositoryTest {

    @Autowired
    private CategoriaRepository categoriaRepository;

    private Categoria categoria;

    @BeforeEach
    public void init() {
        categoria = new Categoria("web");
    }

    @Test
    public void testandoInsercaoNomeNulo() {
        categoria = new Categoria();
        categoriaRepository.save(categoria);
    }

    @Test
    public void testandoConstraintDuplicacaoNome() {
        categoriaRepository.save(categoria);

        try {
            categoriaRepository.save(categoria);
        }catch(ConstraintViolationException constraintViolationException) {
            Assertions.assertThat(constraintViolationException.getMessage()).contains("o nome com o registro fornecido já consta em nosso banco de dados!");
        }
    }

    @Test
    public void testandoInsercaoComPreenchimentoCorreto() {
        categoriaRepository.save(categoria);
        Assertions.assertThat(categoriaRepository.findAll()).hasSize(1);
    }
}
