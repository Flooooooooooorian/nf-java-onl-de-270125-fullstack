package de.neuefische.backend.auth;

import de.neuefische.backend.appuser.AppUser;
import de.neuefische.backend.appuser.AppUserRepository;
import de.neuefische.backend.appuser.AppUserRoles;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final AppUserRepository appUserRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        AppUser appUser = appUserRepository.findById(oAuth2User.getName() /* getName beinhaltet die ID von dem Github-Usere */)
                .orElseGet(() -> saveUser(oAuth2User));
        appUser.setSimpleGrantedAuthorities(List.of(new SimpleGrantedAuthority("ROLE_" + appUser.getRole())));
        return appUser;
    }

    private AppUser saveUser(OAuth2User oAuth2User) {
        return appUserRepository.save(AppUser.builder()
                .id(oAuth2User.getName())
                .username(oAuth2User.getAttributes().get("login").toString())
                .avatarUrl(oAuth2User.getAttributes().get("avatar_url").toString())
                .todoIds(new ArrayList<>())
                .role(AppUserRoles.USER)
                .build()
        );
    }

}
