package com.dbserver.votingchallenge.domain.agenda;
import com.dbserver.votingchallenge.domain.votingSession.VotingSession;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "agendas")
@Entity(name = "agendas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
