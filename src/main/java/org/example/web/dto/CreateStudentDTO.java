package org.example.web.dto;

public record CreateStudentDTO(
        String name,
        Integer age,
        Long groupId
) {}
