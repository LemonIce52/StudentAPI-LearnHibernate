package org.example.web.dto;

import java.util.List;

public record GroupDTO(
        Long id,
        String name,
        Long graduationYear,
        List<StudentDTO> studentList
) {}
