package com.bootcamp.casadocodigo.controllers;

import com.bootcamp.casadocodigo.autor.CadastrarAutorRequest;
import com.bootcamp.casadocodigo.categoria.CadastrarCategoriaRequest;
import com.bootcamp.casadocodigo.livro.CadastrarLivroRequest;
import com.bootcamp.casadocodigo.livro.LivroDetalhesResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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
        cadastrarLivroRequest = testCadastrar(
                "Livro sobre web",
                "É um livro sobre web, java, e outras coisas",
                "1 - Web, 2 - Http",
                BigDecimal.valueOf(29.90),
                156,
                "1000-0000-0000",
                LocalDate.of(2025, 10, 10),
                1l,
                2l);

        cadastrarAutorRequest = new CadastrarAutorRequest("lucas", "lucas.mezuraro@zup.com.br", "desc");
        cadastrarCategoriaRequest = new CadastrarCategoriaRequest("Web");

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

    @Test
    public void testandoInsercaoComNumeroPaginasMenorQue100() throws Exception {

        cadastrarLivroRequest = testCadastrar("Java",
                "É um livro sobre web, java, e outras coisas",
                "1 - Web, 2 - Http",
                BigDecimal.valueOf(19.90),
                90,
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
    public void testandoInsercaoComResumoMaiorQue500Caracteres() throws Exception {

        cadastrarLivroRequest = testCadastrar("Java",
                "É um livro sobre web, java, e outras coisas, É um livro sobre web, java, e outras coisas, É um livro sobre web, java, e outras coisas" +
                        "É um livro sobre web, java, e outras coisas, É um livro sobre web, java, e outras coisas, É um livro sobre web, java, e outras coisas" +
                        "É um livro sobre web, java, e outras coisas, É um livro sobre web, java, e outras coisas, É um livro sobre web, java, e outras coisas" +
                        "É um livro sobre web, java, e outras coisas, É um livro sobre web, java, e outras coisas, É um livro sobre web, java, e outras coisas" +
                        "É um livro sobre web, java, e outras coisas, É um livro sobre web, java, e outras coisas, É um livro sobre web, java, e outras coisas" +
                        "É um livro sobre web, java, e outras coisas, É um livro sobre web, java, e outras coisas, É um livro sobre web, java, e outras coisas" +
                        "É um livro sobre web, java, e outras coisas, É um livro sobre web, java, e outras coisas, É um livro sobre web, java, e outras coisas" +
                        "É um livro sobre web, java, e outras coisas, É um livro sobre web, java, e outras coisas, É um livro sobre web, java, e outras coisas",
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

    @Test
    public void testandoInsercaoComDadosCorretos() throws Exception {

        cadastrarAutorRequest = new CadastrarAutorRequest("lucas2", "lucas.mezuraroa@zup.com.br", "Teste");
        mockMvc.perform(post("/autor/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cadastrarAutorRequest)))
                .andDo(print()).andExpect(status().is2xxSuccessful());

        cadastrarCategoriaRequest.
                setNome("Spring");
        mockMvc.perform(
                post("/categoria/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cadastrarCategoriaRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        Thread.sleep(2000);

        CadastrarLivroRequest cadastrarLivroRequest2 = new CadastrarLivroRequest("Java2",
                "É um livro sobre web, java, e outras coisas",
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
    }

    @Test
    public void testandoExistenciaRotaListarLivros() throws Exception {
        mockMvc.perform(get("/livro/listar")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testandoRotaDetalhesDoLivroEmLivroNaoExistente() throws Exception {
        mockMvc.perform(get("/livro/detalhes/{id}", 100000)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testandoRotaDetalhesDoLivroEmLivroExistente() throws Exception {

        cadastrarAutorRequest = new CadastrarAutorRequest("lucas2", "lucas.mezuraro2@zup.com.br", "Teste");

        this.testandoInsercaoComDadosCorretos();

        mockMvc.perform(get("/livro/detalhes/{id}", 3)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testandoRetornoDetalhesLivro() throws Exception {
        cadastrarAutorRequest = new CadastrarAutorRequest("luiz", "luiz.mezuraroa@zup.com.br", "Teste");
        mockMvc.perform(post("/autor/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cadastrarAutorRequest)))
                .andDo(print()).andExpect(status().is2xxSuccessful());

        cadastrarCategoriaRequest.
                setNome("Spring2");
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



       MvcResult mvcResult = mockMvc.perform(get("/livro/detalhes/{id}", 3)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        LivroDetalhesResponse livroDetalhesResponse = objectMapper.readValue(response, LivroDetalhesResponse.class);

        Assertions.assertThat(cadastrarLivroRequest2.getTitulo()).isEqualTo(livroDetalhesResponse.getTitulo());
        Assertions.assertThat(cadastrarLivroRequest2.getResumo()).isEqualTo(livroDetalhesResponse.getResumo());
        Assertions.assertThat(cadastrarLivroRequest2.getSumario()).isEqualTo(livroDetalhesResponse.getSumario());
        Assertions.assertThat(cadastrarLivroRequest2.getPreco()).isCloseTo(livroDetalhesResponse.getPreco(), Percentage.withPercentage(99.90));
        Assertions.assertThat(cadastrarLivroRequest2.getNumeroPaginas()).isEqualTo(livroDetalhesResponse.getNumeroPaginas());
        Assertions.assertThat(cadastrarLivroRequest2.getIsbn()).isEqualTo(livroDetalhesResponse.getIsbn());
        Assertions.assertThat(cadastrarCategoriaRequest.getNome())
                .isEqualTo(livroDetalhesResponse.getCategoria().getNome());
        Assertions.assertThat(cadastrarAutorRequest.getNome()).isEqualTo(livroDetalhesResponse.getAutor().getNome());
        Assertions.assertThat(cadastrarAutorRequest.getDescricao()).isEqualTo(livroDetalhesResponse.getAutor().getDescricao());
    }


}
