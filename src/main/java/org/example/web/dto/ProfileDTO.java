package org.example.web.dto;

import java.time.LocalDateTime;

public record ProfileDTO(
        Long id,
        String description,
        LocalDateTime lastSeenProfile,
        Long studentId
) {}
