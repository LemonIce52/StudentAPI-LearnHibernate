package org.example.exceptions.dto;

import java.time.LocalDateTime;

public record ExceptionDTO(
        String message,
        String detailMessage,
        LocalDateTime time
) {
}
