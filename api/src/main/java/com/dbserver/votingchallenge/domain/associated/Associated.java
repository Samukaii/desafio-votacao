package com.dbserver.votingchallenge.domain.associated;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "associates")
@Entity(name = "associates")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Associated {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String cpf;
}
