package com.diegogarciadev.runnerz.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.time.LocalDateTime;

public record Run(
        @Id
        Integer id,
        @NotEmpty
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        @Positive
        Integer miles,
        Location location,
        @Version
        Integer version
) {
    public Run {
        if (id == null) {
            throw new IllegalArgumentException("Id must not be null");
        }
        if (title == null) {
            throw new IllegalArgumentException("Title must not be null");
        }
        if (startedOn == null) {
            throw new IllegalArgumentException("Started on must not be null");
        }
        if (completedOn == null) {
            throw new IllegalArgumentException("Completed on must not be null");
        }
        if (miles == null) {
            throw new IllegalArgumentException("Miles must not be null");
        }
        if (location == null) {
            throw new IllegalArgumentException("Location must not be null");
        }
        if (!completedOn.isAfter(startedOn)) {
            throw new IllegalArgumentException("Completed on must be after started on");
        }
    }
}
