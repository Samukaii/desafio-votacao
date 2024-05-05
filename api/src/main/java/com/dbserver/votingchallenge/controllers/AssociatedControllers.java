package com.dbserver.votingchallenge.controllers;

import com.dbserver.votingchallenge.domain.associated.Associated;
import com.dbserver.votingchallenge.domain.associated.AssociatedService;
import com.dbserver.votingchallenge.dtos.associated.AssociatedCreateDTO;
import com.dbserver.votingchallenge.dtos.associated.AssociatedResponseDTO;
import com.dbserver.votingchallenge.mappers.associated.AssociatedMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/associates")
@Tag(name = "Associados")
@RequiredArgsConstructor
public class AssociatedControllers {
    private final AssociatedService associatedService;
    private final AssociatedMapper mapper;

    @Operation(summary = "Cria um associado", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "422", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao realizar a operação")
    })
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

    @Operation(summary = "Lista todos os associados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao realizar a operação")
    })
    @GetMapping
    public ResponseEntity<List<AssociatedResponseDTO>> getAll() {
        return ResponseEntity.ok(mapper.toDtoS(associatedService.getAll()));
    }

    @Operation(summary = "Encontra um associado específico", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Associado não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao realizar a operação")
    })
    @GetMapping("{id}")
    public ResponseEntity<AssociatedResponseDTO> getOne(@PathVariable Integer id) {
        return ResponseEntity.ok(mapper.toDto(associatedService.getOne(id)));
    }
}
