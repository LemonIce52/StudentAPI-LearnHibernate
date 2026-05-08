package org.example.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateGroupDTO(
        @NotBlank(message = "name can't must be null or empty!")
        String name,
        @NotNull(message = "graduationYear can't must be null!")
        @Positive(message = "graduationYear can't must be less zero!")
        Long graduationYear
) {}
