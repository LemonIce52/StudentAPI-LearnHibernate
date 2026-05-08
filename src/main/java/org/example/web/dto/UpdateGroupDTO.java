package org.example.web.dto;

import jakarta.validation.constraints.*;

public record UpdateGroupDTO(
        @NotNull(message = "id can't must be null!")
        @PositiveOrZero(message = "id can't must be less zero!")
        Long id,
        @Pattern(regexp = "^(?!\\s*$).+", message = "name can't must be empty!")
        String name,
        @Positive(message = "graduationYear can't must be less zero!")
        Long graduationYear
) {}
