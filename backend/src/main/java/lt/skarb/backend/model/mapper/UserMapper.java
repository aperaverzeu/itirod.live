package lt.skarb.backend.model.mapper;

import lombok.RequiredArgsConstructor;
import lt.skarb.backend.model.dto.UserDTO;
import lt.skarb.backend.model.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class UserMapper implements Function<UserDTO, User> {
    private final PasswordEncoder passwordEncoder;

    @Override
    public User apply(UserDTO userDTO) {
        return User.builder()
                .username(userDTO.username())
                .email(userDTO.email())
                .password(passwordEncoder.encode(userDTO.password()))
                .build();
    }
}
