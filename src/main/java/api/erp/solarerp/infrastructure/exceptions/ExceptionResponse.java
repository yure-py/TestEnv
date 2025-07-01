package api.erp.solarerp.infrastructure.exceptions;

import java.time.LocalDateTime;

public record ExceptionResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        Object details,
        String path
) {}
