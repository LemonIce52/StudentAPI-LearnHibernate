package org.example.web.dto;

import java.util.List;

public record CourseDTO(
        Long id,
        String name,
        String courseType,
        List<StudentDTO> studentsList
) {}
