package com.dbserver.votingchallenge.controllers;

import com.dbserver.votingchallenge.domain.vote.Vote;
import com.dbserver.votingchallenge.domain.votingSession.VotingSession;
import com.dbserver.votingchallenge.domain.votingSession.VotingSessionService;
import com.dbserver.votingchallenge.dtos.votingSession.VotingSessionCreateDTO;
import com.dbserver.votingchallenge.dtos.votingSession.VotingSessionVoteDTO;
import com.dbserver.votingchallenge.enums.VotingSessionStatus;
import com.dbserver.votingchallenge.fakers.agenda.AgendaFaker;
import com.dbserver.votingchallenge.fakers.vote.VoteFaker;
import com.dbserver.votingchallenge.fakers.votingSession.VotingSessionFaker;
import com.dbserver.votingchallenge.mappers.vote.VoteMapper;
import com.dbserver.votingchallenge.mappers.votingSession.VotingSessionMapper;
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
public class VotingSessionControllersTest {
    @InjectMocks
    VotingSessionControllers votingSessionControllers;

    @Mock
    VotingSessionService votingSessionService;

    private final ObjectMapper mapper = new ObjectMapper();

    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(votingSessionControllers)
                .alwaysDo(print())
                .build();
    }

    @Test
    void shallCreateAVotingSession() throws Exception {
        VotingSessionCreateDTO dto = new VotingSessionCreateDTO(
                46,
                5
        );

        VotingSession expectedVotingSession = VotingSession.builder()
                .id(78)
                .status(VotingSessionStatus.OPENED)
                .agenda(AgendaFaker.createOne())
                .build();

        String requestBody = mapper.writeValueAsString(dto);
        String responseBody = mapper.writeValueAsString(
                VotingSessionMapper.toDto(expectedVotingSession)
        );

        when(votingSessionService.create(dto)).thenReturn(expectedVotingSession);

        mockMvc.perform(post("/voting_sessions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(content().string(responseBody))
                .andExpect(header().string("Location", "http://localhost/voting_sessions/78"));

        verify(votingSessionService).create(dto);
        verifyNoMoreInteractions(votingSessionService);
    }

    @Test
    void shallVote() throws Exception {
        Integer votingSessionId = 91;
        VotingSessionVoteDTO dto = VotingSessionVoteDTO.builder()
                .favorable(false)
                .associatedCpf("621.809.210-04")
                .build();

        Vote expectedVote = VoteFaker.createOne();

        String requestBody = mapper.writeValueAsString(dto);
        String responseBody = mapper.writeValueAsString(
                VoteMapper.toDto(expectedVote)
        );

        when(votingSessionService.vote(votingSessionId, dto)).thenReturn(expectedVote);

        mockMvc.perform(post("/voting_sessions/{id}/vote", votingSessionId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(responseBody));

        verify(votingSessionService).vote(votingSessionId, dto);
        verifyNoMoreInteractions(votingSessionService);
    }

    @Test
    void shallGetAllVotingSessions() throws Exception {
        List<VotingSession> expectedVotingSessions = VotingSessionFaker.createList(5);
        String responseBody = mapper.writeValueAsString(
                VotingSessionMapper.toDtoS(expectedVotingSessions)
        );

        when(votingSessionService.getAll()).thenReturn(expectedVotingSessions);

        mockMvc.perform(get("/voting_sessions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(responseBody));

        verify(votingSessionService).getAll();
        verifyNoMoreInteractions(votingSessionService);
    }

    @Test
    void shallGetOneVotingSession() throws Exception {
        Integer votingSessionId = 91;
        VotingSession expectedVotingSession = VotingSessionFaker.createOne();

        String responseBody = mapper.writeValueAsString(
                VotingSessionMapper.toDto(expectedVotingSession)
        );

        when(votingSessionService.getOne(votingSessionId)).thenReturn(expectedVotingSession);

        mockMvc.perform(get("/voting_sessions/{id}", votingSessionId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(responseBody));

        verify(votingSessionService).getOne(votingSessionId);
        verifyNoMoreInteractions(votingSessionService);
    }
}
