package org.example.web.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public record AddStudentToCourseDTO(
        @NotNull(message = "courseId can't must be null!")
        @PositiveOrZero(message = "courseId can't must be less zero!")
        Long courseId,
        @NotEmpty(message = "studentsId can't must be null or empty!")
        List<Long> studentsId
) {}
