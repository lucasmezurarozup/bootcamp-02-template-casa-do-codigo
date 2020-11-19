package com.bootcamp.casadocodigo.repositories;

import com.bootcamp.casadocodigo.entities.Autor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;

@DataJpaTest
class AutorRepositoryTest {

    @Autowired
    AutorRepository autorRepository;

    Autor autor;

    @BeforeEach
    public void setup() {
        autor = new Autor("lucas", "lucas.mezuraro@zup.com.br", "Teste");
    }

    @Test
    public void testandoInsercaoNovoAutorComCamposEmBranco() throws ConstraintViolationException {
        try {
            autor = new Autor("", "email", "");
            autorRepository.save(autor);
        }catch(Exception exception) {
            Assertions.assertThat(exception).isEqualTo(ConstraintViolationException.class);
        }
    }

    @Test
    public void testandoInsercaoNovoAutorComEmailInvalido() throws ConstraintViolationException {
        try {
            autor = new Autor("lucas", "email", "Teste");
            autorRepository.save(autor);
        }catch(Exception exception) {
            Assertions.assertThat(exception).isEqualTo(ConstraintViolationException.class);
        }
    }

}
