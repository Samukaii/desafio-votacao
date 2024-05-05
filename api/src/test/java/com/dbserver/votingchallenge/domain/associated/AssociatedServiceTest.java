package com.dbserver.votingchallenge.domain.associated;

import com.dbserver.votingchallenge.dtos.associated.AssociatedCreateDTO;
import com.dbserver.votingchallenge.exceptions.associated.AssociatedCpfAlreadyUsedException;
import com.dbserver.votingchallenge.exceptions.associated.AssociatedNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AssociatedServiceTest {
    @InjectMocks
    AssociatedService associatedService;

    @Mock
    AssociatedRepository associatedRepository;

    @Test
    void shallCreateAssociatedSuccessfully() {
        AssociatedCreateDTO dto = new AssociatedCreateDTO(
                "Some associated name",
                "8749847234"
        );

        Associated expectedAssociated = new Associated();
        expectedAssociated.setName(dto.name());
        expectedAssociated.setCpf(dto.cpf());

        when(associatedRepository.save(any(Associated.class)))
                .thenReturn(expectedAssociated);

        Associated result = associatedService.create(dto);

        assertEquals(result, expectedAssociated);

        verify(associatedRepository).findByCpf(dto.cpf());
        verify(associatedRepository).save(any(Associated.class));
        verifyNoMoreInteractions(associatedRepository);
    }

    @Test
    void shallThrowExceptionWhenAssociatedCpfAlreadyUsed() {
        AssociatedCreateDTO dto = new AssociatedCreateDTO(
                "Some associated name",
                "8749847234"
        );

        when(associatedRepository.findByCpf(dto.cpf())).thenReturn(
                Optional.of(new Associated())
        );

        AssociatedCpfAlreadyUsedException e = assertThrows(AssociatedCpfAlreadyUsedException.class, () ->
                associatedService.create(dto)
        );

        assertEquals("Associated cpf already used", e.getMessage());

        verify(associatedRepository).findByCpf(dto.cpf());
        verifyNoMoreInteractions(associatedRepository);
    }

    @Test
    void shallGetAllAssociated() {
        List<Associated> expectedAssociates = new ArrayList<>();
        expectedAssociates.add(new Associated());
        expectedAssociates.add(new Associated());

        when(associatedRepository.findAll()).thenReturn(expectedAssociates);

        List<Associated> result = associatedService.getAll();

        assertEquals(result, expectedAssociates);

        verify(associatedRepository).findAll();
        verifyNoMoreInteractions(associatedRepository);
    }

    @Test
    void shallGetOneAssociated() {
        Integer associatedId = 1;

        Associated expectedAssociated = new Associated();
        expectedAssociated.setId(associatedId);

        when(associatedRepository.findById(associatedId))
                .thenReturn(Optional.of(expectedAssociated));

        Associated result = associatedService.getOne(associatedId);

        assertEquals(result, expectedAssociated);

        verify(associatedRepository).findById(associatedId);
        verifyNoMoreInteractions(associatedRepository);
    }

    @Test
    void shallThrowErrorWhenFindNoAssociated() {
        Integer associatedId = 1;

        when(associatedRepository.findById(associatedId)).thenReturn(Optional.empty());

        final AssociatedNotFoundException e = assertThrows(AssociatedNotFoundException.class, () ->
                associatedService.getOne(associatedId)
        );

        assertEquals("Associated not found", e.getMessage());

        verify(associatedRepository).findById(associatedId);
        verifyNoMoreInteractions(associatedRepository);
    }

    @Test
    void shallGetOneAssociatedByCpf() {
        String cpf = "8273494";

        Associated expectedAssociated = new Associated();
        expectedAssociated.setCpf(cpf);

        when(associatedRepository.findByCpf(cpf))
                .thenReturn(Optional.of(expectedAssociated));

        Associated result = associatedService.getOneByCpf(cpf);

        assertEquals(result, expectedAssociated);

        verify(associatedRepository).findByCpf(cpf);
        verifyNoMoreInteractions(associatedRepository);
    }

    @Test
    void shallThrowErrorWhenFindNoAssociatedByCpf() {
        String cpf = "8273494";

        when(associatedRepository.findByCpf(cpf)).thenReturn(Optional.empty());

        final AssociatedNotFoundException e = assertThrows(AssociatedNotFoundException.class, () ->
                associatedService.getOneByCpf(cpf)
        );

        assertEquals("Associated not found", e.getMessage());

        verify(associatedRepository).findByCpf(cpf);
        verifyNoMoreInteractions(associatedRepository);
    }
}
