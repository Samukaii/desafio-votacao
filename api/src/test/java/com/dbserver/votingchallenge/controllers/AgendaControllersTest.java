package com.dbserver.votingchallenge.controllers;

import com.dbserver.votingchallenge.domain.agenda.Agenda;
import com.dbserver.votingchallenge.domain.agenda.AgendaService;
import com.dbserver.votingchallenge.dtos.agenda.AgendaCreateDTO;
import com.dbserver.votingchallenge.dtos.agenda.AgendaResponseListDTO;
import com.dbserver.votingchallenge.fakers.agenda.AgendaFaker;
import com.dbserver.votingchallenge.mappers.agenda.AgendaMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class AgendaControllersTest {
    @InjectMocks
    AgendaControllers agendaControllers;

    @Mock
    AgendaService agendaService;

    private final ObjectMapper mapper = new ObjectMapper();


    @Mock
    AgendaMapper agendaMapper;

    private final AgendaFaker agendaFaker = new AgendaFaker();


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
                .description("Some agenda description")
                .build();

        Agenda expectedAgenda = Agenda.builder()
                .id(15)
                .name("Some agenda name")
                .description("Some agenda description")
                .build();

        AgendaResponseListDTO expectedDTO = AgendaResponseListDTO.builder()
                .id(expectedAgenda.getId())
                .name(expectedAgenda.getName())
                .description(expectedAgenda.getDescription())
                .build();

        String requestBody = mapper.writeValueAsString(dto);
        String responseBody = mapper.writeValueAsString(expectedDTO);

        when(agendaService.create(dto)).thenReturn(expectedAgenda);
        when(agendaMapper.toDto(any(Agenda.class))).thenReturn(expectedDTO);

        mockMvc.perform(post("/api/v1/agendas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(content().string(responseBody))
                .andExpect(header().string("Location", "http://localhost/api/v1/agendas/15"));

        verify(agendaService).create(dto);
        verifyNoMoreInteractions(agendaService);
    }

    @Test
    void shallGetAllAgendas() throws Exception {
        List<Agenda> expectedAgendas = agendaFaker.createList(5);
        String responseBody = mapper.writeValueAsString(
                agendaMapper.toDtoS(expectedAgendas)
        );

        when(agendaService.getAll()).thenReturn(expectedAgendas);

        mockMvc.perform(get("/api/v1/agendas")
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
        Agenda expectedAgenda = agendaFaker.createOne();

        AgendaResponseListDTO expectedDTO = AgendaResponseListDTO.builder()
                .id(expectedAgenda.getId())
                .name(expectedAgenda.getName())
                .description(expectedAgenda.getDescription())
                .build();

        String responseBody = mapper.writeValueAsString(
                expectedDTO
        );

        when(agendaService.getOne(agendaId)).thenReturn(expectedAgenda);
        when(agendaMapper.toDto(any(Agenda.class))).thenReturn(expectedDTO);

        mockMvc.perform(get("/api/v1/agendas/{id}", agendaId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(responseBody));

        verify(agendaService).getOne(agendaId);
        verifyNoMoreInteractions(agendaService);
    }
}
