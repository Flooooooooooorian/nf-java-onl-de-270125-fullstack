package de.neuefische.backend.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.neuefische.backend.appuser.AppUser;
import de.neuefische.backend.appuser.AppUserRoles;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oidcLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getMe() throws Exception {
        AppUser appUser = AppUser.builder()
                .id("123")
                .username("test-user")
                .avatarUrl("https://www.gidf.de")
                .role(AppUserRoles.ADMIN)
                .todoIds(List.of())
                .build();
        String appUserJson = objectMapper.writeValueAsString(appUser);

        TestingAuthenticationToken authentication = new TestingAuthenticationToken(appUser, null, "ROLE_ADMIN");

        mockMvc.perform(get("/api/auth/me")
                        .with(oidcLogin().userInfoToken(token -> token
                                .claim("login", "test-user")
                                .claim("id", "123")
                                .claim("avatar_url", "https://www.gidf.de")
                        ))
                        .with(SecurityMockMvcRequestPostProcessors.authentication(authentication)))
                .andExpect(status().isOk())
                .andExpect(content().json(appUserJson));
    }
}