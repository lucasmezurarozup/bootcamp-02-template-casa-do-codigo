package com.bootcamp.casadocodigo.controllers;

import com.bootcamp.casadocodigo.cupom.NovoCupomRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CupomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private NovoCupomRequest novoCupomRequest;

    @BeforeEach
    public void init() {
        novoCupomRequest = new NovoCupomRequest("PROMO10", BigDecimal.valueOf(0.10), LocalDate.of(2020, 11, 30));
    }

    @Test
    public void testandoRegistrarComCodigoNulo() throws Exception {
        novoCupomRequest = new NovoCupomRequest(null, BigDecimal.valueOf(0.10), LocalDate.of(2020, 11, 30));
        mockMvc.perform(
                post("/cupom/novo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(novoCupomRequest))
        ).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoRegistrarComPercentualNuloNulo() throws Exception {
        novoCupomRequest = new NovoCupomRequest("PROMO10", null, LocalDate.of(2020, 11, 30));
        mockMvc.perform(
                post("/cupom/novo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(novoCupomRequest))
        ).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoRegistrarComDataValidadeNula() throws Exception {
        novoCupomRequest = new NovoCupomRequest("PROMO10", BigDecimal.valueOf(0.10), null);
        mockMvc.perform(
                post("/cupom/novo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(novoCupomRequest))
        ).andDo(print())
                .andExpect(status().isBadRequest());
    }


    @Test
    public void testandoExistenciaRotaCadastroCupom() throws Exception {
        mockMvc.perform(
                post("/cupom/novo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novoCupomRequest))
        ).andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

}
