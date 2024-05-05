package com.dbserver.votingchallenge.mappers.associated;

import com.dbserver.votingchallenge.domain.associated.Associated;
import com.dbserver.votingchallenge.dtos.associated.AssociatedResponseDTO;
import com.dbserver.votingchallenge.fakers.associated.AssociatedFaker;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class AssociatedMapperTest {
    @InjectMocks
    private AssociatedMapper associatedMapper;

    private final AssociatedFaker associatedFaker = new AssociatedFaker();

    @Test
    void shallConvertToDTO() {
        Associated agenda = associatedFaker.createOne();

        AssociatedResponseDTO dto = associatedMapper.toDto(agenda);

        assertEquals(dto.id(), agenda.getId());
        assertEquals(dto.name(), agenda.getName());
        assertEquals(dto.cpf(), agenda.getCpf());
    }

    @Test
    void shallConvertToDTOList() {
        List<Associated> agendas = associatedFaker.createList(1);

        List<AssociatedResponseDTO> dtoS = associatedMapper.toDtoS(agendas);

        assertEquals(dtoS.get(0).id(), agendas.get(0).getId());
        assertEquals(dtoS.get(0).name(), agendas.get(0).getName());
        assertEquals(dtoS.get(0).cpf(), agendas.get(0).getCpf());
    }
}
