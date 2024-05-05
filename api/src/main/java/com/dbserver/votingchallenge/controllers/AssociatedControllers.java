package com.dbserver.votingchallenge.controllers;

import com.dbserver.votingchallenge.domain.associated.Associated;
import com.dbserver.votingchallenge.domain.associated.AssociatedService;
import com.dbserver.votingchallenge.dtos.associated.AssociatedCreateDTO;
import com.dbserver.votingchallenge.dtos.associated.AssociatedResponseDTO;
import com.dbserver.votingchallenge.mappers.associated.AssociatedMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("associates")
@RequiredArgsConstructor
public class AssociatedControllers {
    private final AssociatedService associatedService;

    @PostMapping
    public ResponseEntity<Associated> createAssociated(
            @RequestBody @Valid AssociatedCreateDTO data,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        Associated associated = associatedService.create(data);

        URI url = uriComponentsBuilder.path("/associates/{associatedId}")
                .buildAndExpand(associated.getId()).toUri();

        return ResponseEntity.created(url).body(associated);
    }

    @GetMapping
    public ResponseEntity<List<AssociatedResponseDTO>> getAll() {
        return ResponseEntity.ok(AssociatedMapper.toDtoS(associatedService.getAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<AssociatedResponseDTO> getOne(@PathVariable Integer id) {
        return ResponseEntity.ok(AssociatedMapper.toDto(associatedService.getOne(id)));
    }
}
