package com.bootcamp.casadocodigo.controllers;

import com.bootcamp.casadocodigo.categoria.CadastrarCategoriaRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private CadastrarCategoriaRequest cadastrarCategoriaRequest;

    @BeforeEach
    public void init() {
        cadastrarCategoriaRequest = new CadastrarCategoriaRequest();
        cadastrarCategoriaRequest.setNome("Web");
    }

    @Test
    public void testandoInsercaoComCampoNomeNulo() throws Exception {
        cadastrarCategoriaRequest.
                setNome(null);
        mockMvc.perform(
                post("/categoria/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cadastrarCategoriaRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoInsercaoComCampoNomeEmBranco() throws Exception {
        cadastrarCategoriaRequest.
                setNome("");
        mockMvc.perform(
                post("/categoria/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cadastrarCategoriaRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoInsercaoComDadosCorretos() throws Exception {
        cadastrarCategoriaRequest.
                setNome("Web");
        mockMvc.perform(
                post("/categoria/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cadastrarCategoriaRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testandoInsercaoComDuplicacaoNome() throws Exception {
        cadastrarCategoriaRequest.
                setNome("Java");
        mockMvc.perform(
                post("/categoria/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cadastrarCategoriaRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(
                post("/categoria/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cadastrarCategoriaRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
