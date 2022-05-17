package lt.skarb.backend.controller;

import lombok.RequiredArgsConstructor;
import lt.skarb.backend.configuration.util.JwtUtil;
import lt.skarb.backend.configuration.util.PBKDF2Encoder;
import lt.skarb.backend.model.dto.AuthRequest;
import lt.skarb.backend.model.dto.AuthResponse;
import lt.skarb.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private static final String NAME = "name";
    private static final String ROLES = "roles";

    private final JwtUtil jwtUtil;
    private final PBKDF2Encoder passwordEncoder;
    private final UserService userService;

    @PostMapping("/login")
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest request) {
        return userService.findUserByUsername(request.getUsername())
                .filter(userDetails -> passwordEncoder
                        .encode(request.getPassword())
                        .equals(userDetails.getPassword()))
                .map(userDetails -> ResponseEntity
                        .ok(new AuthResponse(jwtUtil.generateToken(userDetails))))
                .switchIfEmpty(Mono.just(ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .build()));
    }

//    @GetMapping("/user")
//    public Mono<Map<String, Object>> current(@AuthenticationPrincipal Mono<Principal> principal) {
//        return principal
//                .map(user -> {
//                    Map<String, Object> map = new HashMap<>();
//                    map.put(NAME, user.getName());
//                    map.put(ROLES, authorityListToSet(((Authentication) user).getAuthorities()));
//                    return map;
//                });
//    }

    @GetMapping("/logout")
    public Mono<Void> logout(WebSession session) {
        return session.invalidate();
    }
}

