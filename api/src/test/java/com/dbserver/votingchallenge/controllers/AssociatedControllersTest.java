package com.dbserver.votingchallenge.controllers;

import com.dbserver.votingchallenge.domain.associated.Associated;
import com.dbserver.votingchallenge.domain.associated.AssociatedService;
import com.dbserver.votingchallenge.dtos.associated.AssociatedCreateDTO;
import com.dbserver.votingchallenge.fakers.associated.AssociatedFaker;
import com.dbserver.votingchallenge.mappers.associated.AssociatedMapper;
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
public class AssociatedControllersTest {
    @InjectMocks
    AssociatedControllers associatedControllers;

    @Mock
    AssociatedService associatedService;

    private final ObjectMapper mapper = new ObjectMapper();

    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(associatedControllers)
                .alwaysDo(print())
                .build();
    }

    @Test
    void shallCreateAnAssociated() throws Exception {
        AssociatedCreateDTO dto = new AssociatedCreateDTO(
                "Associated name",
                "999.859.100-79"
        );

        Associated expectedAssociated = Associated.builder()
                .id(21)
                .name("Associated name")
                .cpf("999.859.100-79")
                .build();

        String requestBody = mapper.writeValueAsString(dto);
        String responseBody = mapper.writeValueAsString(
                AssociatedMapper.toDto(expectedAssociated)
        );

        when(associatedService.create(dto)).thenReturn(expectedAssociated);

        mockMvc.perform(post("/associates")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(content().string(responseBody))
                .andExpect(header().string("Location", "http://localhost/associates/21"));

        verify(associatedService).create(dto);
        verifyNoMoreInteractions(associatedService);
    }

    @Test
    void shallGetAllAssociates() throws Exception {
        List<Associated> expectedAssociates = AssociatedFaker.createList(5);
        String responseBody = mapper.writeValueAsString(
                AssociatedMapper.toDtoS(expectedAssociates)
        );

        when(associatedService.getAll()).thenReturn(expectedAssociates);

        mockMvc.perform(get("/associates")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(responseBody));

        verify(associatedService).getAll();
        verifyNoMoreInteractions(associatedService);
    }

    @Test
    void shallGetOneAssociated() throws Exception {
        Integer associatedId = 21;
        Associated expectedAssociated = AssociatedFaker.createOne();

        String responseBody = mapper.writeValueAsString(
                AssociatedMapper.toDto(expectedAssociated)
        );

        when(associatedService.getOne(associatedId)).thenReturn(expectedAssociated);

        mockMvc.perform(get("/associates/{id}", associatedId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(responseBody));

        verify(associatedService).getOne(associatedId);
        verifyNoMoreInteractions(associatedService);
    }
}
