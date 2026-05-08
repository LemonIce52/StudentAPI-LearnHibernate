package org.example.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

public record UpdateProfileDTO(
        @NotNull(message = "id can't must be null!")
        @PositiveOrZero(message = "id can't must be less zero!")
        Long id,
        @Pattern(regexp = "^(?!\\s*$).+", message = "description can't must be empty!")
        String description
) {}
