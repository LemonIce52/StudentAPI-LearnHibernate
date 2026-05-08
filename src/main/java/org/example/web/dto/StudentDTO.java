package org.example.web.dto;

import java.util.List;

public record StudentDTO(
        Long id,
        String name,
        Integer age,
        ProfileDTO profile,
        Long groupId,
        List<CourseDTO> courseList
) {
}
