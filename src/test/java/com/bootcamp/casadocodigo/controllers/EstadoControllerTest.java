package com.bootcamp.casadocodigo.controllers;

import com.bootcamp.casadocodigo.dtos.CadastrarEstadoRequest;
import com.bootcamp.casadocodigo.dtos.CadastrarPaisRequest;
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
public class EstadoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    CadastrarEstadoRequest cadastrarEstadoRequest;

    @BeforeEach
    public void init() {
        cadastrarEstadoRequest = new CadastrarEstadoRequest("São Paulo", 1l);
    }

    @Test
    public void TestandoCadastrarEstadoComNomeNulo() throws Exception {
        cadastrarEstadoRequest = new CadastrarEstadoRequest("", 1l);

        mockMvc.perform(post("/estado/pais/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(cadastrarEstadoRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void TestandoCadastrarEstadoComPaisNulo() throws Exception {
        cadastrarEstadoRequest = new CadastrarEstadoRequest("São Paulo", null);

        mockMvc.perform(post("/estado/pais/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(cadastrarEstadoRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void TestandoCadastrarEstadoComPaisCorretamente() throws Exception {

        CadastrarPaisRequest cadastrarPaisRequest = new CadastrarPaisRequest("EUA");

        mockMvc.perform(post("/pais/registrar")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(cadastrarPaisRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());


        cadastrarEstadoRequest = new CadastrarEstadoRequest("New York", 2l);


        mockMvc.perform(post("/estado/pais/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(cadastrarEstadoRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

}
