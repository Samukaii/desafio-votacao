package com.dbserver.votingchallenge.controllers;

import com.dbserver.votingchallenge.domain.agenda.Agenda;
import com.dbserver.votingchallenge.domain.agenda.AgendaService;
import com.dbserver.votingchallenge.dtos.agenda.AgendaCreateDTO;
import com.dbserver.votingchallenge.dtos.votingSession.VotingSessionResultDTO;
import com.dbserver.votingchallenge.fakers.agenda.AgendaFaker;
import com.dbserver.votingchallenge.mappers.agenda.AgendaMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class AgendaControllersTest {
    @InjectMocks
    AgendaControllers agendaControllers;

    @Mock
    AgendaService agendaService;

    private final ObjectMapper mapper = new ObjectMapper();

    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(agendaControllers)
                .alwaysDo(print())
                .build();
    }

    @Test
    void shallCreateAnAgenda() throws Exception {
        AgendaCreateDTO dto = AgendaCreateDTO.builder()
                .name("Some agenda name")
                .name("Some agenda description")
                .build();

        Agenda expectedAgenda = Agenda.builder()
                .id(15)
                .name("Some agenda name")
                .description("Some agenda description")
                .build();

        String requestBody = mapper.writeValueAsString(dto);
        String responseBody = mapper.writeValueAsString(
                AgendaMapper.toDto(expectedAgenda)
        );

        when(agendaService.create(dto)).thenReturn(expectedAgenda);

        mockMvc.perform(post("/agendas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(content().string(responseBody))
                .andExpect(header().string("Location", "http://localhost/agendas/15"));

        verify(agendaService).create(dto);
        verifyNoMoreInteractions(agendaService);
    }

    @Test
    void shallGetAllAgendas() throws Exception {
        List<Agenda> expectedAgendas = AgendaFaker.createList(5);
        String responseBody = mapper.writeValueAsString(
                AgendaMapper.toDtoS(expectedAgendas)
        );

        when(agendaService.getAll()).thenReturn(expectedAgendas);

        mockMvc.perform(get("/agendas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(responseBody));

        verify(agendaService).getAll();
        verifyNoMoreInteractions(agendaService);
    }

    @Test
    void shallGetOneAgenda() throws Exception {
        Integer agendaId = 1;
        Agenda expectedAgenda = AgendaFaker.createOne();
        VotingSessionResultDTO resultDTO = VotingSessionResultDTO.builder()
                .votesInFavor(1)
                .votesNotInFavor(2)
                .totalVotes(3)
                .build();

        String responseBody = mapper.writeValueAsString(
                AgendaMapper.toSingleDto(expectedAgenda, resultDTO)
        );

        when(agendaService.getOne(agendaId)).thenReturn(expectedAgenda);
        when(agendaService.getResult(agendaId)).thenReturn(resultDTO);

        mockMvc.perform(get("/agendas/{id}", agendaId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(responseBody));

        verify(agendaService).getOne(agendaId);
        verify(agendaService).getResult(agendaId);
        verifyNoMoreInteractions(agendaService);
    }
}
