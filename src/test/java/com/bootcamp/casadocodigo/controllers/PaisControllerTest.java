package com.bootcamp.casadocodigo.controllers;

import com.bootcamp.casadocodigo.localizacao.pais.CadastrarPaisRequest;
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
public class PaisControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    CadastrarPaisRequest cadastrarPaisRequest;

    @BeforeEach
    public void init() {
        cadastrarPaisRequest = new CadastrarPaisRequest("Brasil");
    }

    @Test
    public void TestandoCadastrarPaisComNomeNulo() throws Exception {
        mockMvc.perform(post("/pais/registrar")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void TestandoCadastrarPaisComDadosCorretos() throws Exception {
        mockMvc.perform(post("/pais/registrar")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(cadastrarPaisRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

}
