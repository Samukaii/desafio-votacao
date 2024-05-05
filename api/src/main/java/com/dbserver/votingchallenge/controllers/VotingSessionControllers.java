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
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("voting_sessions")
@RequiredArgsConstructor
public class VotingSessionControllers {
    private final VotingSessionService votingSessionService;

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

    @PostMapping("/{id}/vote")
    public ResponseEntity<VoteResponseDTO> vote(@RequestBody @Valid VotingSessionVoteDTO data, @PathVariable Integer id) {
        Vote vote = votingSessionService.vote(id, data);

        return ResponseEntity.ok(VoteMapper.toDto(vote));
    }

    @GetMapping
    public ResponseEntity<List<VotingSessionResponseDTO>> getAll() {
        List<VotingSessionResponseDTO> all = VotingSessionMapper.toDtoS(votingSessionService.getAll());

        return ResponseEntity.ok(all);
    }

    @GetMapping("{id}")
    public ResponseEntity<VotingSessionResponseDTO> getOne(@PathVariable Integer id) {
        VotingSessionResponseDTO all = VotingSessionMapper.toDto(votingSessionService.getOne(id));

        return ResponseEntity.ok(all);
    }
}
