package de.neuefische.backend.security;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "appUsers")
public record AppUser(
        @Id
        String id,
        String username,
        String password
) {

}
