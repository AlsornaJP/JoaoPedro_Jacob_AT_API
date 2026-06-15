package org.example.joaopedro_jacob_at_java.controller;

import tools.jackson.databind.ObjectMapper;
import org.example.joaopedro_jacob_at_java.model.DTO.EquipeDTO;
import org.example.joaopedro_jacob_at_java.model.Modalidade;
import org.example.joaopedro_jacob_at_java.model.Status;
import org.example.joaopedro_jacob_at_java.service.EquipeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EquipeController.class)
class EquipeControllerTest {

    MockMvc mockMvc;
    ObjectMapper objectMapper;

    @MockitoBean
    EquipeService equipeService;

    @Autowired
    EquipeControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    void deveCriarEquipeERetornar201() throws Exception {
        EquipeDTO equipeDTO = new EquipeDTO();
        equipeDTO.setNome("Flamengo");
        equipeDTO.setSigla("FLA");
        equipeDTO.setModalidade(Modalidade.FUTEBOL);
        equipeDTO.setCategoria("Sub-20");
        equipeDTO.setCapitao("João");
        equipeDTO.setTecnico("Carlos");
        equipeDTO.setQuantidadeDeAtletas(22);
        equipeDTO.setFundacao(LocalDate.of(1895, 11, 17));
        equipeDTO.setStatus(Status.PARTICIPANDO);

        doNothing().when(equipeService).registrarEquipe(any(EquipeDTO.class));

        mockMvc.perform(post("/equipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(equipeDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void deveBuscarEquipePorIdERetornar200() throws Exception {
        EquipeDTO equipeDTO = new EquipeDTO();
        equipeDTO.setId(1);
        equipeDTO.setNome("Flamengo");
        equipeDTO.setSigla("FLA");
        equipeDTO.setModalidade(Modalidade.FUTEBOL);
        equipeDTO.setCategoria("Sub-20");
        equipeDTO.setCapitao("João");
        equipeDTO.setTecnico("Carlos");
        equipeDTO.setQuantidadeDeAtletas(22);
        equipeDTO.setFundacao(LocalDate.of(1895, 11, 17));
        equipeDTO.setStatus(Status.PARTICIPANDO);

        when(equipeService.buscarPorId(1)).thenReturn(equipeDTO);

        mockMvc.perform(get("/equipes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Flamengo"))
                .andExpect(jsonPath("$.sigla").value("FLA"))
                .andExpect(jsonPath("$.modalidade").value("FUTEBOL"));
    }

    @Test
    void deveListarEquipesERetornarArrayNaoVazio() throws Exception {
        EquipeDTO equipeDTO = new EquipeDTO();
        equipeDTO.setId(1);
        equipeDTO.setNome("Flamengo");
        equipeDTO.setSigla("FLA");
        equipeDTO.setModalidade(Modalidade.FUTEBOL);
        equipeDTO.setCategoria("Sub-20");
        equipeDTO.setCapitao("João");
        equipeDTO.setTecnico("Carlos");
        equipeDTO.setQuantidadeDeAtletas(22);
        equipeDTO.setFundacao(LocalDate.of(1895, 11, 17));
        equipeDTO.setStatus(Status.PARTICIPANDO);

        when(equipeService.listarEquipes()).thenReturn(List.of(equipeDTO));

        mockMvc.perform(get("/equipes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[0].nome").value("Flamengo"));
    }

    @Test
    void deveAtualizarEquipeERetornar200() throws Exception {
        EquipeDTO equipeAtualizada = new EquipeDTO();
        equipeAtualizada.setId(1);
        equipeAtualizada.setNome("Flamengo Atualizado");
        equipeAtualizada.setSigla("FLA");
        equipeAtualizada.setModalidade(Modalidade.FUTEBOL);
        equipeAtualizada.setCategoria("Sub-23");
        equipeAtualizada.setCapitao("Pedro");
        equipeAtualizada.setTecnico("Tite");
        equipeAtualizada.setQuantidadeDeAtletas(25);
        equipeAtualizada.setFundacao(LocalDate.of(1895, 11, 17));
        equipeAtualizada.setStatus(Status.PARTICIPANDO);

        when(equipeService.atualizarEquipe(any(Integer.class), any(EquipeDTO.class))).thenReturn(equipeAtualizada);

        mockMvc.perform(put("/equipes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(equipeAtualizada)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Flamengo Atualizado"))
                .andExpect(jsonPath("$.categoria").value("Sub-23"))
                .andExpect(jsonPath("$.capitao").value("Pedro"))
                .andExpect(jsonPath("$.tecnico").value("Tite"))
                .andExpect(jsonPath("$.quantidadeDeAtletas").value(25));
    }

    @Test
    void deveDeletarEquipeERetornar204() throws Exception {
        doNothing().when(equipeService).deletarEquipe(1);

        mockMvc.perform(delete("/equipes/1"))
                .andExpect(status().isNoContent());
    }
}