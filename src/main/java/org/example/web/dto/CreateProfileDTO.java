package org.example.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record CreateProfileDTO(
        @NotBlank(message = "description can't must be null or empty!")
        String description,
        @NotNull(message = "studentId can't must be null!")
        @PositiveOrZero(message = "studentId can't must be less zero!")
        Long studentId
) {}
