package com.bootcamp.casadocodigo;

import com.bootcamp.casadocodigo.controllers.AutorController;
import com.bootcamp.casadocodigo.entities.Autor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureWebMvc
public class AutorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Autor autor;

    @Mock
    private AutorController autorController;

    @BeforeEach
    public void setup() {
        autor = new Autor(null, "lucas.mezuraro@zup.com.br", "Livro sobre HTML");
    }

    @Test
    public void testandoCampoObrigatorioNomeNulo() throws Exception {
        mockMvc.perform(post("/autor/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(autor)))
                .andDo(print()).andExpect(status().isBadRequest());
    }


}
