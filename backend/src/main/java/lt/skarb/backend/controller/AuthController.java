package lt.skarb.backend.controller;

import lombok.RequiredArgsConstructor;
import lt.skarb.backend.configuration.util.JwtUtil;
import lt.skarb.backend.configuration.util.PBKDF2Encoder;
import lt.skarb.backend.model.dto.AuthRequest;
import lt.skarb.backend.model.dto.AuthResponse;
import lt.skarb.backend.model.dto.UserDTO;
import lt.skarb.backend.model.mapper.UserMapper;
import lt.skarb.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final JwtUtil jwtUtil;
    private final PBKDF2Encoder passwordEncoder;
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/login")
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest request) {
        return userService.findUserByUsername(request.username())
                .filter(userDetails -> passwordEncoder
                        .encode(request.password())
                        .equals(userDetails.getPassword()))
                .map(userDetails -> ResponseEntity
                        .ok(new AuthResponse(jwtUtil.generateToken(userDetails))))
                .switchIfEmpty(Mono.just(ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .build()));
    }

    @PostMapping
    public Mono<ResponseEntity<String>> register(@RequestBody UserDTO dto) {
        return userService.register(userMapper.apply(dto))
                .map(user -> ResponseEntity
                        .ok(user.getUsername()))
                .switchIfEmpty(Mono.just(ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .build()));
    }
}

