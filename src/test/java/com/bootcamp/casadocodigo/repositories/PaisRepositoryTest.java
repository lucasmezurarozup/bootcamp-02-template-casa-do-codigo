package com.bootcamp.casadocodigo.repositories;

import com.bootcamp.casadocodigo.entities.Pais;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;

@DataJpaTest
public class PaisRepositoryTest {

    @Autowired
    PaisRepository paisRepository;

    private Pais pais;

    @Test
    public void testandoInsercaoComNomeNulo() {
        pais = new Pais(null);
        try {
            paisRepository.save(pais);
        }catch(ConstraintViolationException constraintViolationException) {
            Assertions.assertThat(constraintViolationException).isInstanceOf(ConstraintViolationException.class);
        }
    }

    @Test
    public void testandoInsercaoComDadosCorretos() {
        pais = new Pais("Brasil");
        paisRepository.save(pais);
        Assertions.assertThat(paisRepository.findAll()).hasSize(1);

    }

    @Test
    public void testandoInsercaoComNomeDuplicado() {
        pais = new Pais("Pais");
        paisRepository.save(pais);
        try {
            paisRepository.save(pais);
        }catch(ConstraintViolationException constraintViolationException) {
            Assertions.assertThat(constraintViolationException).isInstanceOf(ConstraintViolationException.class);
        }
    }
}
