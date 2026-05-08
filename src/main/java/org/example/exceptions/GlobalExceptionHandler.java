package org.example.exceptions;

import jakarta.persistence.NoResultException;
import org.example.exceptions.dto.ExceptionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> serverInternalHandler(Exception e) {
        logger.error("Internal server error: {}", e.getMessage());
        var error = new ExceptionDTO(
                "Internal server error",
                e.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionDTO> argumentExceptionHandler(Exception e) {
        logger.error("Argument error: {}", e.getMessage());
        var error = new ExceptionDTO(
                "Argument error",
                e.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<ExceptionDTO> noResultExceptionHandler(Exception e) {
        logger.error("No result error: {}", e.getMessage());
        var error = new ExceptionDTO(
                "Entity not fount in database",
                e.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDTO> methodArgumentExceptionHandler(Exception e) {
        logger.error("Validation error: {}", e.getMessage());
        var error = new ExceptionDTO(
                "No valid data",
                e.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
