package com.bootcamp.casadocodigo.controllers;

import com.bootcamp.casadocodigo.autor.CadastrarAutorRequest;
import com.bootcamp.casadocodigo.categoria.CadastrarCategoriaRequest;
import com.bootcamp.casadocodigo.cupom.NovoCupomRequest;
import com.bootcamp.casadocodigo.livro.CadastrarLivroRequest;
import com.bootcamp.casadocodigo.localizacao.estado.CadastrarEstadoRequest;
import com.bootcamp.casadocodigo.localizacao.pais.CadastrarPaisRequest;
import com.bootcamp.casadocodigo.compra.NovaCompraItemRequest;
import com.bootcamp.casadocodigo.compra.NovaCompraRequest;
import com.bootcamp.casadocodigo.compra.NovoPedidoRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CompraControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    private NovaCompraRequest novaCompraRequest;
    private NovoPedidoRequest novoPedidoRequest;

    @BeforeEach
    public void init() {
        novoPedidoRequest = new NovoPedidoRequest();
        novaCompraRequest = new NovaCompraRequest(
                "lucas",
                "mezuraro",
                "lucas.mezuraro@zup.com.br",
                "00000000000",
                "São Paulo",
                "Nda",
                "Guarulhos",
                1l,
                2l,
                "99999999",
                "00000000",
                novoPedidoRequest
        );
    }

    @Test
    public void testandoExistenciaRotaDeCompra() throws Exception {

        mockMvc.perform(post("/compra/nova")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    public void inserindoPreCondicaoDeRegistroDePaisEEstado() {

    }

    @Test
    public void testandoRotaCompraComNomeNulo() throws Exception {
        novaCompraRequest = new NovaCompraRequest(
                null,
                "mezuraro",
                "lucas.mezuraro@zup.com.br",
                "00000000000",
                "São Paulo",
                "Nda",
                "Guarulhos",
                1l,
                2l,
                "99999999",
                "00000000",
                novoPedidoRequest);
        mockMvc.perform(post("/compra/nova")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novaCompraRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoRotaCompraComSobrenomeNulo() throws Exception {
        novaCompraRequest = new NovaCompraRequest(
                "lucas",
                null,
                "lucas.mezuraro@zup.com.br",
                "00000000000",
                "São Paulo",
                "Nda",
                "Guarulhos",
                1l,
                2l,
                "99999999",
                "00000000",
                novoPedidoRequest);
        mockMvc.perform(post("/compra/nova")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novaCompraRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoRotaCompraComEmailNulo() throws Exception {
        novoPedidoRequest = new NovoPedidoRequest();
        novaCompraRequest = new NovaCompraRequest(
                "lucas",
                "mezuraro",
                 null,
                "00000000000",
                "São Paulo",
                "Nda",
                "Guarulhos",
                1l,
                2l,
                "99999999",
                "00000000",
                novoPedidoRequest);
        mockMvc.perform(post("/compra/nova")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novaCompraRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoRotaCompraComDocumentoNulo() throws Exception {
        novaCompraRequest = new NovaCompraRequest(
                "lucas",
                "mezuraro",
                "lucas.mezuraro@zup.com.br",
                null,
                "São Paulo",
                "Nda",
                "Guarulhos",
                1l,
                2l,
                "99999999",
                "00000000",
                novoPedidoRequest);
        mockMvc.perform(post("/compra/nova")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novaCompraRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoRotaCompraComEnderecoNulo() throws Exception {
        novaCompraRequest = new NovaCompraRequest(
                "lucas",
                "mezuraro",
                "lucas.mezuraro@zup.com.br",
                "000000000",
                null,
                "Nda",
                "Guarulhos",
                1l,
                2l,
                "99999999",
                "00000000",
                novoPedidoRequest);
        mockMvc.perform(post("/compra/nova")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novaCompraRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoRotaCompraComCidadeNula() throws Exception {
        novaCompraRequest = new NovaCompraRequest(
                "lucas",
                "mezuraro",
                "lucas.mezuraro@zup.com.br",
                "000000000",
                "São Paulo",
                "Nda",
                null,
                1l,
                2l,
                "99999999",
                "00000000",
                novoPedidoRequest);
        mockMvc.perform(post("/compra/nova")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novaCompraRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoRotaCompraComPaisNulo() throws Exception {
        novaCompraRequest = new NovaCompraRequest(
                "lucas",
                "mezuraro",
                "lucas.mezuraro@zup.com.br",
                "000000000",
                "São Paulo",
                "Nda",
                "Guarulhos",
                null,
                2l,
                "99999999",
                "00000000",
                novoPedidoRequest);
        mockMvc.perform(post("/compra/nova")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novaCompraRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoRotaCompraComTelefoneNulo() throws Exception {
        novaCompraRequest = new NovaCompraRequest(
                "lucas",
                "mezuraro",
                "lucas.mezuraro@zup.com.br",
                "000000000",
                "São Paulo",
                "Nda",
                "Guarulhos",
                1l,
                2l,
                null,
                "00000000",
                novoPedidoRequest);
        mockMvc.perform(post("/compra/nova")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novaCompraRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoRotaCompraComEstadoNulo() throws Exception {
        novaCompraRequest = new NovaCompraRequest(
                "lucas",
                "mezuraro",
                "lucas.mezuraro@zup.com.br",
                "000000000",
                "São Paulo",
                "Nda",
                "Guarulhos",
                1l,
                null,
                "99999999",
                "00000000",
                novoPedidoRequest);
        mockMvc.perform(post("/compra/nova")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novaCompraRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoRotaCompraComDocumentoInvalido() throws Exception {
        novaCompraRequest = new NovaCompraRequest(
                "lucas",
                "mezuraro",
                "lucas.mezuraro@zup.com.br",
                "000000000",
                "São Paulo",
                "Nda",
                "Guarulhos",
                1l,
                2l,
                "99999999",
                "00000000",
                novoPedidoRequest);
        mockMvc.perform(post("/compra/nova")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novaCompraRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoRotaCompraComPaisEEstadoNaoExistente() throws Exception {
        novaCompraRequest = new NovaCompraRequest(
                "lucas",
                "mezuraro",
                "lucas.mezuraro@zup.com.br",
                "516.170.390-39",
                "São Paulo",
                "Nda",
                "Guarulhos",
                1l,
                2l,
                "99999999",
                "00000000",
                novoPedidoRequest);
        mockMvc.perform(post("/compra/nova")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novaCompraRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoRotaCompraComDadosCorretos() throws Exception {

        CadastrarAutorRequest cadastrarAutorRequest = new CadastrarAutorRequest("luiz", "luiz.mezuraroa@zup.com.br", "Teste");
        mockMvc.perform(post("/autor/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cadastrarAutorRequest)))
                .andDo(print()).andExpect(status().is2xxSuccessful());

        CadastrarCategoriaRequest cadastrarCategoriaRequest = new CadastrarCategoriaRequest("Java3");
        mockMvc.perform(
                post("/categoria/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cadastrarCategoriaRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        CadastrarLivroRequest cadastrarLivroRequest2 = new CadastrarLivroRequest("Java3",
                "E um livro sobre web, java, e outras coisas",
                "1 - Web, 2 - Http",
                BigDecimal.valueOf(29.90),
                156,
                "1000-0000-0000",
                LocalDate.of(2025, 10, 10),
                2l,
                1l);
        mockMvc.perform(post("/livro/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cadastrarLivroRequest2)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());



         mockMvc.perform(get("/livro/detalhes/{id}", 3)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        CadastrarPaisRequest cadastrarPaisRequest = new CadastrarPaisRequest("EUA");

        mockMvc.perform(post("/pais/registrar")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(cadastrarPaisRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());


        CadastrarEstadoRequest cadastrarEstadoRequest = new CadastrarEstadoRequest("New York", 4l);


        mockMvc.perform(post("/estado/pais/{id}", 4l)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(cadastrarEstadoRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());


        BigDecimal precoLivroTest = BigDecimal.valueOf(29.90).setScale(2);

        List<NovaCompraItemRequest> listItens = new ArrayList<>();
        listItens.add(new NovaCompraItemRequest(3l, 1));
        novoPedidoRequest = new NovoPedidoRequest(precoLivroTest, listItens, null);

        novaCompraRequest = new NovaCompraRequest(
                "lucas",
                "mezuraro",
                "lucas.mezuraro@zup.com.br",
                "516.170.390-39",
                "São Paulo",
                "Nda",
                "Guarulhos",
                4l,
                5l,
                "99999999",
                "00000000",
                novoPedidoRequest);
        mockMvc.perform(post("/compra/nova")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novaCompraRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testandoRotaCompraComCupomInvalido() throws Exception {

        CadastrarAutorRequest cadastrarAutorRequest = new CadastrarAutorRequest("luiz", "luiz.mezuraroa@zup.com.br", "Teste");
        mockMvc.perform(post("/autor/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cadastrarAutorRequest)))
                .andDo(print()).andExpect(status().is2xxSuccessful());

        CadastrarCategoriaRequest cadastrarCategoriaRequest = new CadastrarCategoriaRequest("Java3");
        mockMvc.perform(
                post("/categoria/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cadastrarCategoriaRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        CadastrarLivroRequest cadastrarLivroRequest2 = new CadastrarLivroRequest("Java3",
                "E um livro sobre web, java, e outras coisas",
                "1 - Web, 2 - Http",
                BigDecimal.valueOf(29.90),
                156,
                "1000-0000-0000",
                LocalDate.of(2025, 10, 10),
                2l,
                1l);
        mockMvc.perform(post("/livro/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cadastrarLivroRequest2)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());



        mockMvc.perform(get("/livro/detalhes/{id}", 3)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        CadastrarPaisRequest cadastrarPaisRequest = new CadastrarPaisRequest("EUA");

        mockMvc.perform(post("/pais/registrar")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(cadastrarPaisRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());


        CadastrarEstadoRequest cadastrarEstadoRequest = new CadastrarEstadoRequest("New York", 4l);


        mockMvc.perform(post("/estado/pais/{id}", 4l)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(cadastrarEstadoRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());


        BigDecimal precoLivroTest = BigDecimal.valueOf(29.90).setScale(2);

        List<NovaCompraItemRequest> listItens = new ArrayList<>();
        listItens.add(new NovaCompraItemRequest(3l, 1));
        novoPedidoRequest = new NovoPedidoRequest(precoLivroTest, listItens, "PROMO200");

        novaCompraRequest = new NovaCompraRequest(
                "lucas",
                "mezuraro",
                "lucas.mezuraro@zup.com.br",
                "516.170.390-39",
                "São Paulo",
                "Nda",
                "Guarulhos",
                4l,
                5l,
                "99999999",
                "00000000",
                novoPedidoRequest);
        mockMvc.perform(post("/compra/nova")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novaCompraRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoRotaCompraComCupomCadastradoSemAlterarPreco() throws Exception {

        CadastrarAutorRequest cadastrarAutorRequest = new CadastrarAutorRequest("luiz", "luiz.mezuraroa@zup.com.br", "Teste");
        mockMvc.perform(post("/autor/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cadastrarAutorRequest)))
                .andDo(print()).andExpect(status().is2xxSuccessful());

        CadastrarCategoriaRequest cadastrarCategoriaRequest = new CadastrarCategoriaRequest("Java3");
        mockMvc.perform(
                post("/categoria/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cadastrarCategoriaRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        CadastrarLivroRequest cadastrarLivroRequest2 = new CadastrarLivroRequest("Java3",
                "E um livro sobre web, java, e outras coisas",
                "1 - Web, 2 - Http",
                BigDecimal.valueOf(29.90),
                156,
                "1000-0000-0000",
                LocalDate.of(2025, 10, 10),
                2l,
                1l);
        mockMvc.perform(post("/livro/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cadastrarLivroRequest2)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());



        mockMvc.perform(get("/livro/detalhes/{id}", 3)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        CadastrarPaisRequest cadastrarPaisRequest = new CadastrarPaisRequest("EUA");

        mockMvc.perform(post("/pais/registrar")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(cadastrarPaisRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());


        CadastrarEstadoRequest cadastrarEstadoRequest = new CadastrarEstadoRequest("New York", 4l);


        mockMvc.perform(post("/estado/pais/{id}", 4l)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(cadastrarEstadoRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());


        BigDecimal precoLivroTest = BigDecimal.valueOf(29.90).setScale(2);

        List<NovaCompraItemRequest> listItens = new ArrayList<>();
        listItens.add(new NovaCompraItemRequest(3l, 1));

        NovoCupomRequest novoCupomRequest = new NovoCupomRequest("PROMO20", BigDecimal.valueOf(0.20), LocalDate.of(2020, 12, 31));


        mockMvc.perform(post("/cupom/novo")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(novoCupomRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        novoPedidoRequest = new NovoPedidoRequest(precoLivroTest, listItens, "PROMO20");



        novaCompraRequest = new NovaCompraRequest(
                "lucas",
                "mezuraro",
                "lucas.mezuraro@zup.com.br",
                "516.170.390-39",
                "São Paulo",
                "Nda",
                "Guarulhos",
                4l,
                5l,
                "99999999",
                "00000000",
                novoPedidoRequest);
        mockMvc.perform(post("/compra/nova")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novaCompraRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
