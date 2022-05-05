package lt.skarb.backend.controller;

import lombok.RequiredArgsConstructor;
import lt.skarb.backend.model.dto.UserDTO;
import lt.skarb.backend.model.entity.User;
import lt.skarb.backend.model.mapper.UserMapper;
import lt.skarb.backend.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/register")
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("")
    public Mono<User> register(@Valid @RequestBody UserDTO user) {
        return userService.register(userMapper.apply(user));
    }
}
