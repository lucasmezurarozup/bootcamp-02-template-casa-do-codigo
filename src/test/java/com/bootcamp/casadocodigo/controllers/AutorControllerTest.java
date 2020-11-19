package com.bootcamp.casadocodigo.controllers;

import com.bootcamp.casadocodigo.controllers.AutorController;
import com.bootcamp.casadocodigo.dtos.CadastrarAutorRequest;
import com.bootcamp.casadocodigo.entities.Autor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AutorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Autor autor;

    private CadastrarAutorRequest cadastrarAutorRequest;

    @BeforeEach
    public void setup() {
        cadastrarAutorRequest = new CadastrarAutorRequest("lucas", "lucas.mezuraro@zup.com.br", "Livro sobre HTML");
    }

    @Test
    public void testandoCampoObrigatorioNomeNulo() throws Exception {

        cadastrarAutorRequest = new CadastrarAutorRequest(null, "lucas.mezuraro@zup.com.br", "Livro sobre HTML");

        mockMvc.perform(post("/autor/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cadastrarAutorRequest)))
                .andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void testandoCampoObrigatorioEmailNulo() throws Exception {

        cadastrarAutorRequest = new CadastrarAutorRequest("lucas",null, "Livro sobre HTML");

        mockMvc.perform(post("/autor/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cadastrarAutorRequest)))
                .andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void testandoCampoObrigatorioDescricaoNulo() throws Exception {

        cadastrarAutorRequest = new CadastrarAutorRequest("lucas","lucas.mezuraro@zup.com.br", null);

        mockMvc.perform(post("/autor/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cadastrarAutorRequest)))
                .andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void testandoCampoObrigatorioDescricaoEmBranco() throws Exception {

        cadastrarAutorRequest = new CadastrarAutorRequest("lucas","lucas.mezuraro@zup.com.br", "");

        mockMvc.perform(post("/autor/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cadastrarAutorRequest)))
                .andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void testandoCampoObrigatorioNomeEmBranco() throws Exception {

        cadastrarAutorRequest = new CadastrarAutorRequest("","lucas.mezuraro@zup.com.br", "Teste");

        mockMvc.perform(post("/autor/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cadastrarAutorRequest)))
                .andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void testandoCampoObrigatorioEmailEmBranco() throws Exception {

        cadastrarAutorRequest = new CadastrarAutorRequest("lucas","", "Teste");

        mockMvc.perform(post("/autor/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cadastrarAutorRequest)))
                .andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void testandoCampoObrigatorioEmailInvalido() throws Exception {

        cadastrarAutorRequest = new CadastrarAutorRequest("lucas","luc", "Teste");

        mockMvc.perform(post("/autor/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cadastrarAutorRequest)))
                .andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void testandoCampoObrigatorioDescricaoCaracteresAcimaDoPermitido() throws Exception {
        cadastrarAutorRequest = new CadastrarAutorRequest("lucas","lucas.mezuraro@zup.com.br", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut nec tellus iaculis, posuere lectus in, mattis dui. Etiam id turpis quis mi malesuada porttitor. Proin ante nunc, porttitor et enim id, aliquam scelerisque dui. Suspendisse lorem felis, laoreet eget hendrerit quis, laoreet et diam. Donec vulputate porta cursus. Ut quis diam maximus, varius ligula aliquet, interdum mauris. Donec augue nulla, suscipit eget neque vel, varius rhoncus orci. Fusce feugiat enim at dui congue accumsan.\n" +
                "\n" +
                "Proin nibh elit, ullamcorper eu purus quis, molestie dapibus mi. Nullam sit amet felis fermentum, pulvinar ante quis, laoreet nulla. Integer a sapien eu augue congue vulputate eget ut massa. Nullam dictum, libero eu maximus fringilla, sem sem ultrices orci, consequat rhoncus velit sem at dui. Phasellus lobortis est dolor, ut commodo tellus fermentum nec. Nulla sodales facilisis felis eget tempor. Nullam enim lacus, consectetur sed cursus sit amet, suscipit nec justo. Integer commodo mattis nibh ut mattis. Donec tellus eros, faucibus ac ultrices tempor, vehicula ut orci.");

        mockMvc.perform(post("/autor/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cadastrarAutorRequest)))
                .andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void testandoCampoObrigatorioDataCriacaoNaoNulo() throws Exception {
        cadastrarAutorRequest = new CadastrarAutorRequest("lucas", "lucas.mezuraro@zup.com.br", "Teste");
        Optional<LocalDateTime> dataCriacao = Optional.ofNullable(cadastrarAutorRequest.toObject().getDataCriacao());

        Assert.notNull(dataCriacao, "");

    }

    @Test
    public void testandoCondicaoDeSucessoRegistrandoAutor() throws Exception {
        cadastrarAutorRequest = new CadastrarAutorRequest("lucas", "lucas.mezuraro@zup.com.br", "Teste");
        mockMvc.perform(post("/autor/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cadastrarAutorRequest)))
                .andDo(print()).andExpect(status().is2xxSuccessful());
    }


}
