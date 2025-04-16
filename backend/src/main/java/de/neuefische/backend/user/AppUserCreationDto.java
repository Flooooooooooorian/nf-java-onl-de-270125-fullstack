package de.neuefische.backend.user;


import jakarta.validation.constraints.*;

import java.time.Instant;

public record AppUserCreationDto(

        @NotNull
        @Size(min = 3, max = 20)
        String username,

        @NotBlank
        @Size(min = 8)
        String password,

        @Email
        @NotNull
        String email,

        @Past
        Instant birthDate
) {

}
