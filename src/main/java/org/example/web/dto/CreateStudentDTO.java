package org.example.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record CreateStudentDTO(
        @NotBlank(message = "name can't must be null or empty!")
        String name,
        @NotNull(message = "age can't must be null!")
        @Positive(message = "age can't must be less zero!")
        Integer age,
        @NotNull(message = "groupId can't must be null!")
        @PositiveOrZero(message = "groupId can't must be less zero!")
        Long groupId
) {}
