package com.dbserver.votingchallenge.domain.vote;

import com.dbserver.votingchallenge.domain.associated.Associated;
import com.dbserver.votingchallenge.domain.votingSession.VotingSession;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "votes")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private boolean favorable;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "associated_id", referencedColumnName = "id")
    private Associated associated;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "voting_session_id", referencedColumnName = "id")
    private VotingSession votingSession;
}
