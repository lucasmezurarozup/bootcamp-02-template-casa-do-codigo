package com.bootcamp.casadocodigo.controllers;

import com.bootcamp.casadocodigo.dtos.CadastrarLivroRequest;
import com.bootcamp.casadocodigo.entities.Autor;
import com.bootcamp.casadocodigo.entities.Categoria;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import java.time.temporal.TemporalAccessor;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LivroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private CadastrarLivroRequest cadastrarLivroRequest;

    @BeforeEach
    public void init() {
        cadastrarLivroRequest = new CadastrarLivroRequest(
                "Livro sobre web",
                "É um livro sobre web, java, e outras coisas",
                "1 - Web, 2 - Http",
                BigDecimal.valueOf(29.90),
                156,
                "1000-0000-0000",
                LocalDate.of(2025, 10, 10),
                new Categoria("Web"),
                new Autor("Lucas", "lucas.mezuraro@zup.com.br", "Autor sobre web")
        );
    }

    @Test
    public void testandoInsercaoComTituloNulo() throws Exception {
        cadastrarLivroRequest = new CadastrarLivroRequest(null,
                "É um livro sobre web, java, e outras coisas",
                "1 - Web, 2 - Http",
                BigDecimal.valueOf(29.90),
                156,
                "1000-0000-0000",
                LocalDate.of(2025, 10, 10),
                new Categoria("Web"),
                new Autor("Lucas", "lucas.mezuraro@zup.com.br", "Autor sobre web"));
        mockMvc.perform(post("/livro/registrar")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(cadastrarLivroRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoInsercaoComResumoNulo() throws Exception {
        cadastrarLivroRequest = new CadastrarLivroRequest("Java",
                null,
                "1 - Web, 2 - Http",
                BigDecimal.valueOf(29.90),
                156,
                "1000-0000-0000",
                LocalDate.of(2025, 10, 10),
                new Categoria("Web"),
                new Autor("Lucas", "lucas.mezuraro@zup.com.br", "Autor sobre web"));
        mockMvc.perform(post("/livro/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cadastrarLivroRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoInsercaoComSumarioNulo() throws Exception {
        cadastrarLivroRequest = new CadastrarLivroRequest("Java",
                "É um livro sobre web, java, e outras coisas",
                null,
                BigDecimal.valueOf(29.90),
                156,
                "1000-0000-0000",
                LocalDate.of(2025, 10, 10),
                new Categoria("Web"),
                new Autor("Lucas", "lucas.mezuraro@zup.com.br", "Autor sobre web"));
        mockMvc.perform(post("/livro/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cadastrarLivroRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoInsercaoComPrecoNulo() throws Exception {
        cadastrarLivroRequest = new CadastrarLivroRequest("Java",
                "É um livro sobre web, java, e outras coisas",
                "1 - Web, 2 - Http",
                null,
                156,
                "1000-0000-0000",
                LocalDate.of(2025, 10, 10),
                new Categoria("Web"),
                new Autor("Lucas", "lucas.mezuraro@zup.com.br", "Autor sobre web"));
        mockMvc.perform(post("/livro/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cadastrarLivroRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoInsercaoComNumeroPaginasNulo() throws Exception {
        cadastrarLivroRequest = new CadastrarLivroRequest("Java",
                "É um livro sobre web, java, e outras coisas",
                "1 - Web, 2 - Http",
                BigDecimal.valueOf(29.90),
                null,
                "1000-0000-0000",
                LocalDate.of(2025, 10, 10),
                new Categoria("Web"),
                new Autor("Lucas", "lucas.mezuraro@zup.com.br", "Autor sobre web"));
        mockMvc.perform(post("/livro/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cadastrarLivroRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoInsercaoComIsbnNulo() throws Exception {
        cadastrarLivroRequest = new CadastrarLivroRequest("Java",
                "É um livro sobre web, java, e outras coisas",
                "1 - Web, 2 - Http",
                BigDecimal.valueOf(29.90),
                156,
                null,
                LocalDate.of(2025, 10, 10),
                new Categoria("Web"),
                new Autor("Lucas", "lucas.mezuraro@zup.com.br", "Autor sobre web"));
        mockMvc.perform(post("/livro/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cadastrarLivroRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoInsercaoComDataPublicacaoNulo() throws Exception {
        cadastrarLivroRequest = new CadastrarLivroRequest("Java",
                "É um livro sobre web, java, e outras coisas",
                "1 - Web, 2 - Http",
                BigDecimal.valueOf(29.90),
                156,
                "1000-0000-0000",
                null,
                new Categoria("Web"),
                new Autor("Lucas", "lucas.mezuraro@zup.com.br", "Autor sobre web"));
        mockMvc.perform(post("/livro/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cadastrarLivroRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoInsercaoComCategoriaNulo() throws Exception {
        cadastrarLivroRequest = new CadastrarLivroRequest("Java",
                "É um livro sobre web, java, e outras coisas",
                "1 - Web, 2 - Http",
                BigDecimal.valueOf(29.90),
                156,
                "1000-0000-0000",
                LocalDate.of(2025, 10, 10),
                null,
                new Autor("Lucas", "lucas.mezuraro@zup.com.br", "Autor sobre web"));
        mockMvc.perform(post("/livro/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cadastrarLivroRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoInsercaoComAutorNulo() throws Exception {
        cadastrarLivroRequest = new CadastrarLivroRequest("Java",
                "É um livro sobre web, java, e outras coisas",
                "1 - Web, 2 - Http",
                BigDecimal.valueOf(29.90),
                156,
                "1000-0000-0000",
                LocalDate.of(2025, 10, 10),
                new Categoria("Web"),
                null);
        mockMvc.perform(post("/livro/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cadastrarLivroRequest)))
                .andExpect(status().isBadRequest());
    }
}
