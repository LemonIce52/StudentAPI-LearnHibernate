package org.example.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

public record UpdateCourseDTO(
        @NotNull(message = "id can't must be null!")
        @PositiveOrZero(message = "id can't must be less zero!")
        Long id,
        @Pattern(regexp = "^(?!\\s*$).+", message = "name can't must be empty!")
        String name,
        @Pattern(regexp = "^(?!\\s*$).+", message = "courseType can't must be empty!")
        String courseType
) {
}
