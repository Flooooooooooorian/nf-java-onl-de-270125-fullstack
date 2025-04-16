package de.neuefische.backend.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.neuefische.backend.exception.ErrorMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;
    private final ObjectMapper objectMapper;

    @PostMapping
    public AppUser postAppUser(@RequestBody @Valid AppUserCreationDto appUser) {
        return appUserService.createAppUser(new AppUser(null, appUser.username(), appUser.password()));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handelMethodArgumentNotValidException(MethodArgumentNotValidException e) throws JsonProcessingException {
        return new ErrorMessage(Instant.now(),
                "Registration failed!",
                e.getBindingResult().getFieldErrors().stream()
                        .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                        .toList()
        );
    }
}
