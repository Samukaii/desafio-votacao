package com.dbserver.votingchallenge.mappers.associated;

import com.dbserver.votingchallenge.domain.associated.Associated;
import com.dbserver.votingchallenge.dtos.associated.AssociatedResponseDTO;
import com.dbserver.votingchallenge.fakers.associated.AssociatedFaker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AssociatedMapperTest {
    @Test
    void shallConvertToDTO() {
        Associated agenda = AssociatedFaker.createOne();

        AssociatedResponseDTO dto = AssociatedMapper.toDto(agenda);

        assertEquals(dto.id(), agenda.getId());
        assertEquals(dto.name(), agenda.getName());
        assertEquals(dto.cpf(), agenda.getCpf());
    }

    @Test
    void shallConvertToDTOList() {
        List<Associated> agendas = AssociatedFaker.createList(1);

        List<AssociatedResponseDTO> dtoS = AssociatedMapper.toDtoS(agendas);

        assertEquals(dtoS.get(0).id(), agendas.get(0).getId());
        assertEquals(dtoS.get(0).name(), agendas.get(0).getName());
        assertEquals(dtoS.get(0).cpf(), agendas.get(0).getCpf());
    }
}
