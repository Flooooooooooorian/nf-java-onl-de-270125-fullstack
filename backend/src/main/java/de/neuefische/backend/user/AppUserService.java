package de.neuefische.backend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;

    public AppUser createAppUser(AppUser appUser) {
        return appUserRepository.save(appUser);
    }
}
