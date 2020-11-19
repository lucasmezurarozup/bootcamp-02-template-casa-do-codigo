package com.bootcamp.casadocodigo.repositories;

import com.bootcamp.casadocodigo.entities.Autor;
import org.assertj.core.api.Assertions;
import org.hibernate.JDBCException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;

@DataJpaTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository autorRepository;

    Autor autor;

    @BeforeEach
    public void setup() {
        autor = new Autor("lucas", "lucas.mezuraro@zup.com.br", "Teste");
    }

    @Test
    public void testandoInsercaoNovoAutorComCampoNomeNulo() throws ConstraintViolationException {
        try {
            autor = new Autor(null, "email@email.com", "Teste");
            autorRepository.save(autor);
        }catch(Exception exception) {
            Assertions.assertThat(exception).isEqualTo(ConstraintViolationException.class);
        }
    }

    @Test
    public void testandoInsercaoNovoAutorComCampoNomeEmBranco() throws ConstraintViolationException {
        try {
            autor = new Autor("", "email@email.com", "Teste");
            autorRepository.save(autor);
        }catch(Exception exception) {
            Assertions.assertThat(exception).isEqualTo(ConstraintViolationException.class);
        }
    }

    @Test
    public void testandoInsercaoNovoAutorComCampoEmailNulo() throws ConstraintViolationException {
        try {
            autor = new Autor("lucas", null, "Teste");
            autorRepository.save(autor);
        }catch(Exception exception) {
            Assertions.assertThat(exception).isEqualTo(ConstraintViolationException.class);
        }
    }

    @Test
    public void testandoInsercaoNovoAutorComCampoEmailEmBranco() throws ConstraintViolationException {
        try {
            autor = new Autor("lucas", "", "Teste");
            autorRepository.save(autor);
        }catch(Exception exception) {
            Assertions.assertThat(exception).isEqualTo(ConstraintViolationException.class);
        }
    }

    @Test
    public void testandoInsercaoNovoAutorComCampoDescricaoNulo() throws ConstraintViolationException {
        try {
            autor = new Autor("lucas", "lucas@lucas.com", "");
            autorRepository.save(autor);
        }catch(Exception exception) {
            Assertions.assertThat(exception).isEqualTo(ConstraintViolationException.class);
        }
    }

    @Test
    public void testandoInsercaoNovoAutorComCampoDescricaoEmBranco() throws ConstraintViolationException {
        try {
            autor = new Autor("lucas", "lucas@lucas.com", "");
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

    @Test
    public void testandoInsercaoNovoAutorComDescricaoComCaracteresAlemDoPermitido() throws ConstraintViolationException {
        try {
            autor = new Autor("lucas", "email", "\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut nec tellus iaculis, posuere lectus in, mattis dui. Etiam id turpis quis mi malesuada porttitor. Proin ante nunc, porttitor et enim id, aliquam scelerisque dui. Suspendisse lorem felis, laoreet eget hendrerit quis, laoreet et diam. Donec vulputate porta cursus. Ut quis diam maximus, varius ligula aliquet, interdum mauris. Donec augue nulla, suscipit eget neque vel, varius rhoncus orci. Fusce feugiat enim at dui congue accumsan.\\n\" +\n" +
                    "                \"\\n\" +\n" +
                    "                \"Proin nibh elit, ullamcorper eu purus quis, molestie dapibus mi. Nullam sit amet felis fermentum, pulvinar ante quis, laoreet nulla. Integer a sapien eu augue congue vulputate eget ut massa. Nullam dictum, libero eu maximus fringilla, sem sem ultrices orci, consequat rhoncus velit sem at dui. Phasellus lobortis est dolor, ut commodo tellus fermentum nec. Nulla sodales facilisis felis eget tempor. Nullam enim lacus, consectetur sed cursus sit amet, suscipit nec justo. Integer commodo mattis nibh ut mattis. Donec tellus eros, faucibus ac ultrices tempor, vehicula ut orci.\"");
            autorRepository.save(autor);
        }catch(Exception exception) {
            Assertions.assertThat(exception).isEqualTo(ConstraintViolationException.class);
        }
    }

    @Test
    public void tentativaRegistrarAutorComSucesso() {
        autorRepository.save(autor);
        Assertions.assertThat(autorRepository.findAll()).hasSize(1);
    }

    @Test
    public void testandoValidacaoDuplicacaoAutor() {
        autorRepository.save(autor);
        Assertions.assertThat(autorRepository.findAll()).hasSize(1);

        try {
            autorRepository.save(autor);
        }catch(JDBCException exception) {
            Assertions.assertThat(exception).isInstanceOf(JDBCException.class);
        }
    }

}
