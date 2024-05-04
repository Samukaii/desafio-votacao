package com.dbserver.votingchallenge.controllers;

import com.dbserver.votingchallenge.domain.agenda.Agenda;
import com.dbserver.votingchallenge.domain.agenda.AgendaService;
import com.dbserver.votingchallenge.dtos.agenda.AgendaCreateDTO;
import com.dbserver.votingchallenge.dtos.agenda.AgendaResponseListDTO;
import com.dbserver.votingchallenge.dtos.agenda.AgendaResponseSingleDTO;
import com.dbserver.votingchallenge.dtos.votingSession.VotingSessionResultDTO;
import com.dbserver.votingchallenge.mappers.agenda.AgendaMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("agendas")
@RequiredArgsConstructor
public class AgendaControllers {
    private final AgendaService agendaService;

    @PostMapping
    public ResponseEntity<Agenda> createAgenda(
            @RequestBody @Valid AgendaCreateDTO data,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        Agenda agenda = agendaService.create(data);

        URI url = uriComponentsBuilder.path("/agendas/{agendaId}")
                .buildAndExpand(agenda.getId()).toUri();

        return ResponseEntity.created(url).body(agenda);
    }

    @GetMapping
    public ResponseEntity<List<AgendaResponseListDTO>> findAll() {
        return ResponseEntity.ok(AgendaMapper.toDtoS(agendaService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaResponseSingleDTO> findOne(@PathVariable Integer id) {
        Agenda agenda = agendaService.getOne(id);
        VotingSessionResultDTO result = agendaService.getResult(id);

        return ResponseEntity.ok(AgendaMapper.toSingleDto(agenda, result));
    }
}
