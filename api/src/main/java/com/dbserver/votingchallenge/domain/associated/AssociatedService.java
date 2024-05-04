package com.dbserver.votingchallenge.domain.associated;

import com.dbserver.votingchallenge.dtos.associated.AssociatedCreateDTO;
import com.dbserver.votingchallenge.exceptions.associated.AssociatedCpfAlreadyUsedException;
import com.dbserver.votingchallenge.exceptions.associated.AssociatedNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssociatedService {
    private final AssociatedRepository associatedRepository;

    public Associated create(AssociatedCreateDTO data) {
        validateCpfAlreadyInUse(data.cpf());
        Associated associated = new Associated();

        associated.setName(data.name());
        associated.setCpf(data.cpf());

        associatedRepository.save(associated);

        return associated;
    }

    public List<Associated> getAll() {
        return associatedRepository.findAll();
    }

    public Associated getOne(Integer id) {
        return associatedRepository.findById(id).orElseThrow(AssociatedNotFoundException::new);
    }

    public Associated getOneByCpf(String cpf) {
        return associatedRepository.findByCpf(cpf).orElseThrow(AssociatedNotFoundException::new);
    }

    private void validateCpfAlreadyInUse(String cpf) {
        Optional<Associated> associated = associatedRepository.findByCpf(cpf);

        if(associated.isPresent())
            throw new AssociatedCpfAlreadyUsedException();
    }
}
