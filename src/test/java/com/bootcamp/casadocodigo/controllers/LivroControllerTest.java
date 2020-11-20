package com.bootcamp.casadocodigo.controllers;

import com.bootcamp.casadocodigo.dtos.CadastrarAutorRequest;
import com.bootcamp.casadocodigo.dtos.CadastrarCategoriaRequest;
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

    private CadastrarAutorRequest cadastrarAutorRequest;
    private CadastrarCategoriaRequest cadastrarCategoriaRequest;

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
                1l,
                1l);

        cadastrarAutorRequest = new CadastrarAutorRequest("lucas", "lucas.mezuraro@zup.com.br", "desc");
        cadastrarCategoriaRequest = new CadastrarCategoriaRequest();
        cadastrarCategoriaRequest.setNome("Java");
    }

    public CadastrarLivroRequest testCadastrar(
            String titulo,
            String resumo,
            String sumario,
            BigDecimal preco,
            Integer numeroPaginas,
            String isbn,
            LocalDate dataPublicacao,
            Long idCategoria,
            Long idAutor) {

        return new CadastrarLivroRequest(titulo,
                resumo,
                sumario,
                preco,
                numeroPaginas,
                isbn,
                dataPublicacao,
                idCategoria,
                idAutor);
    }

    @Test
    public void testandoInsercaoComTituloNulo() throws Exception {
        cadastrarLivroRequest = testCadastrar(null,
                "É um livro sobre web, java, e outras coisas",
                "1 - Web, 2 - Http",
                BigDecimal.valueOf(29.90),
                156,
                "1000-0000-0000",
                LocalDate.of(2025, 10, 10),
                1l,
                1l);
        mockMvc.perform(post("/livro/registrar")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(cadastrarLivroRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoInsercaoComResumoNulo() throws Exception {
        cadastrarLivroRequest = testCadastrar("Java",
                null,
                "1 - Web, 2 - Http",
                BigDecimal.valueOf(29.90),
                156,
                "1000-0000-0000",
                LocalDate.of(2025, 10, 10),
                1l,
                1l);
        mockMvc.perform(post("/livro/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cadastrarLivroRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoInsercaoComSumarioNulo() throws Exception {
        cadastrarLivroRequest = testCadastrar("Java",
                "É um livro sobre web, java, e outras coisas",
                null,
                BigDecimal.valueOf(29.90),
                156,
                "1000-0000-0000",
                LocalDate.of(2025, 10, 10),
                1l,
                1l);
        mockMvc.perform(post("/livro/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cadastrarLivroRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoInsercaoComPrecoNulo() throws Exception {
        cadastrarLivroRequest = testCadastrar("Java",
                "É um livro sobre web, java, e outras coisas",
                "1 - Web, 2 - Http",
                null,
                156,
                "1000-0000-0000",
                LocalDate.of(2025, 10, 10),
                1l,
                1l);
        mockMvc.perform(post("/livro/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cadastrarLivroRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoInsercaoComNumeroPaginasNulo() throws Exception {
        cadastrarLivroRequest = testCadastrar("Java",
                "É um livro sobre web, java, e outras coisas",
                "1 - Web, 2 - Http",
                BigDecimal.valueOf(29.90),
                null,
                "1000-0000-0000",
                LocalDate.of(2025, 10, 10),
                1l,
                1l);
        mockMvc.perform(post("/livro/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cadastrarLivroRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoInsercaoComIsbnNulo() throws Exception {
        cadastrarLivroRequest = testCadastrar("Java",
                "É um livro sobre web, java, e outras coisas",
                "1 - Web, 2 - Http",
                BigDecimal.valueOf(29.90),
                156,
                null,
                LocalDate.of(2025, 10, 10),
                1l,
                1l);
        mockMvc.perform(post("/livro/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cadastrarLivroRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoInsercaoComDataPublicacaoNulo() throws Exception {
        cadastrarLivroRequest = testCadastrar("Java",
                "É um livro sobre web, java, e outras coisas",
                "1 - Web, 2 - Http",
                BigDecimal.valueOf(29.90),
                156,
                "1000-0000-0000",
                null,
                1l,
                1l);
        mockMvc.perform(post("/livro/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cadastrarLivroRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoInsercaoComCategoriaNulo() throws Exception {
        cadastrarLivroRequest = testCadastrar("Java",
                "É um livro sobre web, java, e outras coisas",
                "1 - Web, 2 - Http",
                BigDecimal.valueOf(29.90),
                156,
                "1000-0000-0000",
                LocalDate.of(2025, 10, 10),
                null,
                1l);
        mockMvc.perform(post("/livro/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cadastrarLivroRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoInsercaoComAutorNulo() throws Exception {
        cadastrarLivroRequest = testCadastrar("Java",
                "É um livro sobre web, java, e outras coisas",
                "1 - Web, 2 - Http",
                BigDecimal.valueOf(29.90),
                156,
                "1000-0000-0000",
                LocalDate.of(2025, 10, 10),
                1l,
                null);
        mockMvc.perform(post("/livro/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cadastrarLivroRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoInsercaoComPrecoMenorQue20() throws Exception {

        cadastrarLivroRequest = testCadastrar("Java",
                "É um livro sobre web, java, e outras coisas",
                "1 - Web, 2 - Http",
                BigDecimal.valueOf(19.90),
                156,
                "1000-0000-0000",
                LocalDate.of(2025, 10, 10),
                1l,
                1l);

        mockMvc.perform(post("/livro/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cadastrarLivroRequest)))
                .andExpect(status().isBadRequest());
    }
}