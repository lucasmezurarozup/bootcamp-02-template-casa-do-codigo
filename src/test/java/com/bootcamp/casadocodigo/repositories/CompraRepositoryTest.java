package com.bootcamp.casadocodigo.repositories;

import com.bootcamp.casadocodigo.autor.Autor;
import com.bootcamp.casadocodigo.autor.AutorRepository;
import com.bootcamp.casadocodigo.categoria.Categoria;
import com.bootcamp.casadocodigo.categoria.CategoriaRepository;
import com.bootcamp.casadocodigo.compra.*;
import com.bootcamp.casadocodigo.livro.CadastrarLivroRequest;
import com.bootcamp.casadocodigo.livro.Livro;
import com.bootcamp.casadocodigo.livro.LivroRepository;
import com.bootcamp.casadocodigo.localizacao.estado.CadastrarEstadoRequest;
import com.bootcamp.casadocodigo.localizacao.estado.Estado;
import com.bootcamp.casadocodigo.localizacao.estado.EstadoRepository;
import com.bootcamp.casadocodigo.localizacao.pais.CadastrarPaisRequest;
import com.bootcamp.casadocodigo.localizacao.pais.Pais;
import com.bootcamp.casadocodigo.localizacao.pais.PaisRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class CompraRepositoryTest {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private PaisRepository paisRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    private Compra compra;
    private List<PedidoItem> pedidoItems;
    private Pedido pedido;
    private Estado estado;
    private Pais pais;
    private Livro livro;
    private Autor autor;
    private Categoria categoria;

    @BeforeEach
    public void init() {

        pais = paisRepository.save(new Pais("EUA"));


        estado = estadoRepository.save(new Estado("New York", pais));
        categoria = categoriaRepository.save(new Categoria("Web"));

        autor = autorRepository.save(new Autor("lucas",
                "lucas.mezuraro@zup.com.br",
                "Test"));
        livro = livroRepository.save(new Livro("Java3",
                "E um livro sobre web, java, e outras coisas",
                "1 - Web, 2 - Http",
                BigDecimal.valueOf(29.90),
                156,
                "1000-0000-0000",
                LocalDate.of(2025, 10, 10),
                categoria,
                autor));
        BigDecimal precoLivroTest = BigDecimal.valueOf(29.90).setScale(2);

        pedidoItems = new ArrayList<>();
        pedidoItems.add(new PedidoItem(livro, 1));
        pedido = new Pedido(precoLivroTest, pedidoItems, null);


        compra = new Compra(
                "lucas",
                "mezuraro",
                "lucas.mezuraro@zup.com.br",
                "516.170.390-39",
                "São Paulo",
                "Nda",
                "Guarulhos",
                pais,
                estado,
                "99999999",
                "00000000",
                pedido);
    }

    @Test
    public void testarInsercaoComNomeClienteNulo() {

        compra = new Compra(
                null,
                "mezuraro",
                "lucas.mezuraro@zup.com.br",
                "516.170.390-39",
                "São Paulo",
                "Nda",
                "Guarulhos",
                pais,
                estado,
                "99999999",
                "00000000",
                pedido);

        try {
            compraRepository.save(compra);
        } catch(ConstraintViolationException constraintViolationException) {
            Assertions.assertThat(constraintViolationException.getMessage()).contains("nome é um campo de preenchimento obrigatório.");
        }

    }

    @Test
    public void testarInsercaoComSobrenomeClienteNulo() {

        compra = new Compra(
                "lucas",
                null,
                "lucas.mezuraro@zup.com.br",
                "516.170.390-39",
                "São Paulo",
                "Nda",
                "Guarulhos",
                pais,
                estado,
                "99999999",
                "00000000",
                pedido);

        try {
            compraRepository.save(compra);
        } catch(ConstraintViolationException constraintViolationException) {
            Assertions.assertThat(constraintViolationException.getMessage()).contains("sobrenome é um campo de preenchimento obrigatório.");
        }

    }

    @Test
    public void testarInsercaoComEmailClienteNulo() {

        compra = new Compra(
                "lucas",
                "mezuraro",
                null,
                "516.170.390-39",
                "São Paulo",
                "Nda",
                "Guarulhos",
                pais,
                estado,
                "99999999",
                "00000000",
                pedido);

        try {
            compraRepository.save(compra);
        } catch(ConstraintViolationException constraintViolationException) {
            Assertions.assertThat(constraintViolationException.getMessage()).contains("email é um campo de preenchimento obrigatório.");
        }

    }

    @Test
    public void testarInsercaoComDocumentoClienteNulo() {

        compra = new Compra(
                "lucas",
                "mezuraro",
                "lucas.mezuraro@zup.com.br",
                null,
                "São Paulo",
                "Nda",
                "Guarulhos",
                pais,
                estado,
                "99999999",
                "00000000",
                pedido);

        try {
            compraRepository.save(compra);
        } catch(ConstraintViolationException constraintViolationException) {
            Assertions.assertThat(constraintViolationException.getMessage()).contains("é um campo de preenchimento obrigatório.");
        }

    }

    @Test
    public void testarInsercaoComEnderecoClienteNulo() {

        compra = new Compra(
                "lucas",
                "mezuraro",
                "lucas.mezuraro@zup.com.br",
                "516.170.390-39",
                null,
                "Nda",
                "Guarulhos",
                pais,
                estado,
                "99999999",
                "00000000",
                pedido);

        try {
            compraRepository.save(compra);
        } catch(ConstraintViolationException constraintViolationException) {
            Assertions.assertThat(constraintViolationException.getMessage()).contains("é um campo de preenchimento obrigatório.");
        }

    }

    @Test
    public void testarInsercaoComCidadeClienteNulo() {

        compra = new Compra(
                "lucas",
                "mezuraro",
                "lucas.mezuraro@zup.com.br",
                "516.170.390-39",
                "São Paulo",
                "Nda",
                null,
                pais,
                estado,
                "99999999",
                "00000000",
                pedido);

        try {
            compraRepository.save(compra);
        } catch(ConstraintViolationException constraintViolationException) {
            Assertions.assertThat(constraintViolationException.getMessage()).contains("é um campo de preenchimento obrigatório.");
        }

    }

    @Test
    public void testarInsercaoComPaisClienteNulo() {

        compra = new Compra(
                "lucas",
                "mezuraro",
                "lucas.mezuraro@zup.com.br",
                "516.170.390-39",
                "São Paulo",
                "Nda",
                "Guarulhos",
                null,
                estado,
                "99999999",
                "00000000",
                pedido);

        try {
            compraRepository.save(compra);
        } catch(ConstraintViolationException constraintViolationException) {
            Assertions.assertThat(constraintViolationException.getMessage()).contains("é um campo de preenchimento obrigatório.");
        }

    }

    @Test
    public void testarInsercaoComEstadoClienteNulo() {

        compra = new Compra(
                "lucas",
                "mezuraro",
                "lucas.mezuraro@zup.com.br",
                "516.170.390-39",
                "São Paulo",
                "Nda",
                "Guarulhos",
                pais,
                null,
                "99999999",
                "00000000",
                pedido);

        try {
            compraRepository.save(compra);
        } catch(ConstraintViolationException constraintViolationException) {
            Assertions.assertThat(constraintViolationException.getMessage()).contains("é um campo de preenchimento obrigatório.");
        }

    }

    @Test
    public void testarInsercaoComTelefoneClienteNulo() {

        compra = new Compra(
                "lucas",
                "mezuraro",
                "lucas.mezuraro@zup.com.br",
                "516.170.390-39",
                "São Paulo",
                "Nda",
                "Guarulhos",
                pais,
                estado,
                null,
                "00000000",
                pedido);

        try {
            compraRepository.save(compra);
        } catch(ConstraintViolationException constraintViolationException) {
            Assertions.assertThat(constraintViolationException.getMessage()).contains("é um campo de preenchimento obrigatório.");
        }

    }

    @Test
    public void testarInsercaoComPedidoClienteNulo() {

        compra = new Compra(
                "lucas",
                "mezuraro",
                "lucas.mezuraro@zup.com.br",
                "516.170.390-39",
                "São Paulo",
                "Nda",
                "Guarulhos",
                pais,
                estado,
                "99999999",
                "00000000",
                null);

        try {
            compraRepository.save(compra);
        } catch(ConstraintViolationException constraintViolationException) {
            Assertions.assertThat(constraintViolationException.getMessage()).contains("é um campo de preenchimento obrigatório.");
        }

    }
}
