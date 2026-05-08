package org.example.web.dto;

import jakarta.validation.constraints.*;

public record UpdateStudentDTO(
        @NotNull(message = "id can't must be null!")
        @PositiveOrZero(message = "id can't must be less zero!")
        Long id,
        @Pattern(regexp = "^(?!\\s*$).+", message = "name can't must be empty!")
        String name,
        @Positive(message = "age can't must be less zero!")
        Integer age,
        @PositiveOrZero(message = "groupId can't must be less zero!")
        Long groupId
) {}
