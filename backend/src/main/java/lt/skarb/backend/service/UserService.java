package lt.skarb.backend.service;

import lt.skarb.backend.model.entity.User;
import lt.skarb.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public record UserService(UserRepository userRepository) {
    public Mono<User> findUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public Mono<User> register(User user) {
        // todo add validation
        return this.userRepository.save(user);
    }
}
