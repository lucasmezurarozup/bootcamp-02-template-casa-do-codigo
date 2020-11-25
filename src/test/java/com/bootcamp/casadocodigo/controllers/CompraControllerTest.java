package com.bootcamp.casadocodigo.controllers;

import com.bootcamp.casadocodigo.localizacao.estado.CadastrarEstadoRequest;
import com.bootcamp.casadocodigo.localizacao.pais.CadastrarPaisRequest;
import com.bootcamp.casadocodigo.pagamento.NovaCompraRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CompraControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    private NovaCompraRequest novaCompraRequest;

    @BeforeEach
    public void init() {
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
                "00000000"
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
                "00000000");
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
                "00000000");
        mockMvc.perform(post("/compra/nova")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novaCompraRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoRotaCompraComEmailNulo() throws Exception {
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
                "00000000");
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
                "00000000");
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
                "00000000");
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
                "00000000");
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
                "00000000");
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
                "00000000");
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
                "00000000");
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
                "00000000");
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
                "00000000");
        mockMvc.perform(post("/compra/nova")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novaCompraRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoRotaCompraComDadosCorretos() throws Exception {

        CadastrarPaisRequest cadastrarPaisRequest = new CadastrarPaisRequest("EUA");

        mockMvc.perform(post("/pais/registrar")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(cadastrarPaisRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());


        CadastrarEstadoRequest cadastrarEstadoRequest = new CadastrarEstadoRequest("New York", 1l);


        mockMvc.perform(post("/estado/pais/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(cadastrarEstadoRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());


        novaCompraRequest = new NovaCompraRequest(
                "lucas",
                "mezuraro",
                "lucas.mezuraro@zup.com.br",
                "516.170.390-39",
                "São Paulo",
                "Nda",
                "Guarulhos",
                1l,
                1l,
                "99999999",
                "00000000");
        mockMvc.perform(post("/compra/nova")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novaCompraRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
