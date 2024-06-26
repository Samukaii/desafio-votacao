package com.dbserver.votingchallenge.domain.votingSession;

import com.dbserver.votingchallenge.domain.agenda.Agenda;
import com.dbserver.votingchallenge.domain.vote.Vote;
import com.dbserver.votingchallenge.enums.VotingSessionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "voting_sessions")
@Entity(name = "voting_sessions")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class VotingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private VotingSessionStatus status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agenda_id", referencedColumnName = "id")
    private Agenda agenda;

    @OneToMany(mappedBy = "votingSession")
    private List<Vote> votes;
}
