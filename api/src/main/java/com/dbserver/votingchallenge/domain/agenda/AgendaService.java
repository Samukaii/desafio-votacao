package com.dbserver.votingchallenge.domain.agenda;

import com.dbserver.votingchallenge.domain.vote.VoteService;
import com.dbserver.votingchallenge.dtos.agenda.AgendaCreateDTO;
import com.dbserver.votingchallenge.dtos.votingSession.VotingSessionResultDTO;
import com.dbserver.votingchallenge.exceptions.agenda.AgendaNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendaService {
    private final AgendaRepository agendaRepository;
    private final VoteService voteService;

    public Agenda create(AgendaCreateDTO data) {
        Agenda newAgenda = new Agenda();

        newAgenda.setName(data.name());
        newAgenda.setDescription(data.description());

        agendaRepository.save(newAgenda);

        return newAgenda;
    }

    public List<Agenda> getAll() {
        return agendaRepository.findAll();
    }

    public VotingSessionResultDTO getResult(Integer id) {
        Agenda agenda = getOne(id);

        return voteService.getResult(agenda.getVotingSession());
    }

    public Agenda getOne(Integer id) {
        return agendaRepository.findById(id).orElseThrow(AgendaNotFoundException::new);
    }
}
