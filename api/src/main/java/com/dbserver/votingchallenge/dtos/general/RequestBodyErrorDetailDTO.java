package com.dbserver.votingchallenge.dtos.general;

public record RequestBodyErrorDetailDTO(
        String fieldName,
        String message
) {
}
