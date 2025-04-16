package de.neuefische.backend.exception;

import java.time.Instant;
import java.util.List;

public record ErrorMessage(
        Instant timestamp,
        String message,
        List<String> details
) {
}
