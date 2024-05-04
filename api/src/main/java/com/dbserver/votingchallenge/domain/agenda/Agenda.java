package com.dbserver.votingchallenge.domain.agenda;
import com.dbserver.votingchallenge.domain.voting.VotingSession;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "agendas")
@Entity(name = "agendas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @OneToOne(mappedBy = "agenda")
    private VotingSession votingSession;
}
