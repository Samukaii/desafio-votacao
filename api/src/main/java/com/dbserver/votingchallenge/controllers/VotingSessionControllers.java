package com.dbserver.votingchallenge.controllers;

import com.dbserver.votingchallenge.domain.vote.Vote;
import com.dbserver.votingchallenge.domain.votingSession.VotingSession;
import com.dbserver.votingchallenge.domain.votingSession.VotingSessionService;
import com.dbserver.votingchallenge.dtos.vote.VoteResponseDTO;
import com.dbserver.votingchallenge.dtos.votingSession.VotingSessionCreateDTO;
import com.dbserver.votingchallenge.dtos.votingSession.VotingSessionResponseDTO;
import com.dbserver.votingchallenge.dtos.votingSession.VotingSessionVoteDTO;
import com.dbserver.votingchallenge.mappers.vote.VoteMapper;
import com.dbserver.votingchallenge.mappers.votingSession.VotingSessionMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/voting_sessions")
@Tag(name = "Seção de votação")
@RequiredArgsConstructor
public class VotingSessionControllers {
    private final VotingSessionService votingSessionService;

    @Operation(summary = "Abre uma sessão de votação para uma pauta específica", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "422", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "404", description = "Pauta não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao realizar a operação")
    })
    @PostMapping
    public ResponseEntity<VotingSessionResponseDTO> create(
            @RequestBody @Valid VotingSessionCreateDTO data,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        VotingSession votingSession = votingSessionService.create(data);

        URI url = uriComponentsBuilder.path("/voting_sessions/{voting_session_id}")
                .buildAndExpand(votingSession.getId()).toUri();

        return ResponseEntity.created(url).body(VotingSessionMapper.toDto(votingSession));
    }


    @Operation(summary = "Vota em uma pauta específica", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "429", description = "Usuário já votou nesta pauta"),
            @ApiResponse(responseCode = "422", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao realizar a operação")
    })
    @PostMapping("/{id}/vote")
    public ResponseEntity<VoteResponseDTO> vote(@RequestBody @Valid VotingSessionVoteDTO data, @PathVariable Integer id) {
        Vote vote = votingSessionService.vote(id, data);

        return ResponseEntity.ok(VoteMapper.toDto(vote));
    }

    @Operation(summary = "Lista todas as sessões de pautas abertas", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao realizar a operação", useReturnTypeSchema = true)
    })
    @GetMapping
    public ResponseEntity<List<VotingSessionResponseDTO>> getAll() {
        List<VotingSessionResponseDTO> all = VotingSessionMapper.toDtoS(votingSessionService.getAll());

        return ResponseEntity.ok(all);
    }

    @Operation(summary = "Encontra uma sessão de votação específica", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Associado não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao realizar a operação")
    })
    @GetMapping("{id}")
    public ResponseEntity<VotingSessionResponseDTO> getOne(@PathVariable Integer id) {
        VotingSessionResponseDTO all = VotingSessionMapper.toDto(votingSessionService.getOne(id));

        return ResponseEntity.ok(all);
    }
}
