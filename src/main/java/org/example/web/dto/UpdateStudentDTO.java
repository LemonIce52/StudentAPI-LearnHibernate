package org.example.web.dto;

public record UpdateStudentDTO(
        Long id,
        String name,
        Integer age,
        Long groupId
) {}
