package com.dbserver.votingchallenge.controllers;

import com.dbserver.votingchallenge.domain.agenda.Agenda;
import com.dbserver.votingchallenge.domain.agenda.AgendaService;
import com.dbserver.votingchallenge.dtos.agenda.AgendaCreateDTO;
import com.dbserver.votingchallenge.dtos.agenda.AgendaResponseListDTO;
import com.dbserver.votingchallenge.dtos.agenda.AgendaResponseSingleDTO;
import com.dbserver.votingchallenge.dtos.votingSession.VotingSessionResultDTO;
import com.dbserver.votingchallenge.mappers.agenda.AgendaMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/agendas", produces = {"application/json"})
@Tag(name = "Pautas")
@RequiredArgsConstructor
public class AgendaControllers {
    private final AgendaService agendaService;

    @Operation(summary = "Cria uma pauta", method = "POST")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Ok"),
            @ApiResponse( responseCode = "422", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao realizar a operação")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AgendaResponseListDTO> createAgenda(
            @RequestBody @Valid AgendaCreateDTO data,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        Agenda agenda = agendaService.create(data);

        URI url = uriComponentsBuilder.path("/agendas/{agendaId}")
                .buildAndExpand(agenda.getId()).toUri();

        return ResponseEntity.created(url).body(AgendaMapper.toDto(agenda));
    }

    @Operation(summary = "Lista todas as pautas", method = "GET")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao realizar a operação")
    })
    @GetMapping
    public ResponseEntity<List<AgendaResponseListDTO>> findAll() {
        return ResponseEntity.ok(AgendaMapper.toDtoS(agendaService.getAll()));
    }

    @Operation(summary = "Encontra uma pauta específica", method = "GET")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Ok"),
            @ApiResponse( responseCode = "404", description = "Pauta não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao realizar a operação")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AgendaResponseSingleDTO> findOne(@PathVariable Integer id) {
        Agenda agenda = agendaService.getOne(id);
        VotingSessionResultDTO result = agendaService.getResult(id);

        return ResponseEntity.ok(AgendaMapper.toSingleDto(agenda, result));
    }
}
