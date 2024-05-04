package com.dbserver.votingchallenge.domain.associated;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssociatedRepository extends JpaRepository<Associated, Integer> {
    public Optional<Associated> findByCpf(String cpf);
}
