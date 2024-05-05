package com.dbserver.votingchallenge.domain.agenda;

import com.dbserver.votingchallenge.domain.vote.VoteService;
import com.dbserver.votingchallenge.domain.votingSession.VotingSession;
import com.dbserver.votingchallenge.domain.votingSession.VotingSessionService;
import com.dbserver.votingchallenge.dtos.agenda.AgendaCreateDTO;
import com.dbserver.votingchallenge.dtos.agenda.AgendaOpenVotingSessionDTO;
import com.dbserver.votingchallenge.dtos.agenda.AgendaVoteDTO;
import com.dbserver.votingchallenge.dtos.votingSession.VotingSessionResultDTO;
import com.dbserver.votingchallenge.enums.VotingSessionStatus;
import com.dbserver.votingchallenge.exceptions.agenda.AgendaAlreadyOpenVotingSessionException;
import com.dbserver.votingchallenge.exceptions.agenda.AgendaNotFoundException;
import com.dbserver.votingchallenge.exceptions.agenda.AgendaVotingSessionClosedException;
import com.dbserver.votingchallenge.exceptions.agenda.AgendaVotingSessionNotOpenedException;
import com.dbserver.votingchallenge.mappers.agenda.AgendaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendaService {
    private final AgendaRepository agendaRepository;
    private final VoteService voteService;
    private final VotingSessionService votingSessionService;
    private final AgendaMapper agendaMapper;

    public Agenda create(AgendaCreateDTO data) {
        Agenda newAgenda = agendaMapper.toEntity(data);

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

    public Agenda openVotingSession(Integer agendaId, AgendaOpenVotingSessionDTO dto) {
        Agenda agenda = getOne(agendaId);

        if(agenda.getVotingSession() != null)
            throw new AgendaAlreadyOpenVotingSessionException();

        votingSessionService.create(agenda, dto.timeInMinutes());

        return getOne(agendaId);
    }

    public Agenda vote(Integer agendaId, AgendaVoteDTO dto) {
        Agenda agenda = getOne(agendaId);

        VotingSession votingSession = agenda.getVotingSession();

        if(agenda.getVotingSession() == null)
            throw new AgendaVotingSessionNotOpenedException();

        if(votingSession.getStatus() != VotingSessionStatus.OPENED)
            throw new AgendaVotingSessionClosedException();

        voteService.create(votingSession, dto);

        return agenda;
    }
}
