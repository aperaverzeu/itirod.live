package lt.skarb.backend.model.mapper;

import lt.skarb.backend.model.dto.UserDTO;
import lt.skarb.backend.model.entity.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserMapper implements Function<UserDTO, User> {
    @Override
    public User apply(UserDTO userDTO) {
        return User.builder()
                .username(userDTO.username())
                .email(userDTO.email())
                .password(userDTO.password())
                .build();
    }
}
