package org.example.web.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateCourseDTO(
        @NotBlank(message = "name can't must be null or empty!")
        String name,
        @NotBlank(message = "courseType can't must be null or empty!")
        String courseType
) {
}
