package lt.skarb.backend.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.core.authority.AuthorityUtils.authorityListToSet;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private static final String NAME = "name";
    private static final String ROLES = "roles";

    @GetMapping("/user")
    public Mono<Map<String, Object>> current(@AuthenticationPrincipal Mono<Principal> principal) {
        return principal
                .map(user -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put(NAME, user.getName());
                    map.put(ROLES, authorityListToSet(((Authentication) user).getAuthorities()));
                    return map;
                });
    }

    @GetMapping("/logout")
    public Mono<Void> logout(WebSession session) {
        return session.invalidate();
    }
}

