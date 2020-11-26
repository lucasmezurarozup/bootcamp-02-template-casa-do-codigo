package com.bootcamp.casadocodigo.repositories;

import com.bootcamp.casadocodigo.cupom.Cupom;
import com.bootcamp.casadocodigo.cupom.CupomRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.time.LocalDate;

@DataJpaTest
public class CupomRepositoryTest {

    @Autowired
    private CupomRepository cupomRepository;

    private Cupom cupom;

    @Test
    public void testandoInsercaoComCodigoNulo() {
        try {
            cupom = new Cupom(null, BigDecimal.valueOf(0.10), LocalDate.of(2020, 11, 30));
            cupomRepository.save(cupom);
        } catch(ConstraintViolationException constraintViolationException) {
            Assertions.assertThat(constraintViolationException.getMessage()).contains("é um campo de preenchimento obrigatório.");
        }
    }

    @Test
    public void testandoInsercaoComPercentualDescontoNulo() {
        try {
            cupom = new Cupom("PROMO10", null, LocalDate.of(2020, 11, 30));
            cupomRepository.save(cupom);
        } catch(ConstraintViolationException constraintViolationException) {
            Assertions.assertThat(constraintViolationException.getMessage()).contains("é um campo de preenchimento obrigatório.");
        }
    }

    @Test
    public void testandoInsercaoComDataValidadeNulo() {
        try {
            cupom = new Cupom("PROMO10", BigDecimal.valueOf(0.10), null);
            cupomRepository.save(cupom);
        } catch(ConstraintViolationException constraintViolationException) {
            Assertions.assertThat(constraintViolationException.getMessage()).contains("é um campo de preenchimento obrigatório.");
        }
    }

    @Test
    public void testandoInsercaoComDataValidadeNoPassado() {
        try {
            cupom = new Cupom("PROMO10", BigDecimal.valueOf(0.10), LocalDate.of(2000, 10, 10));
            cupomRepository.save(cupom);
        } catch(Exception e) {
            Assertions.assertThat(e).isInstanceOf(ConstraintViolationException.class);
        }
    }

    @Test
    public void testandoInsercaoComPercentualNegativo() {
        try {
            cupom = new Cupom("PROMO10", BigDecimal.valueOf((-0.10)), LocalDate.of(2020, 11, 30));
            cupomRepository.save(cupom);
        } catch(Exception e) {
            Assertions.assertThat(e).isInstanceOf(ConstraintViolationException.class);
        }
    }
}
