package lt.skarb.backend.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.skarb.backend.model.entity.Course;
import lt.skarb.backend.model.entity.Post;
import lt.skarb.backend.model.entity.User;
import lt.skarb.backend.repository.CourseRepository;
import lt.skarb.backend.repository.PostRepository;
import lt.skarb.backend.repository.UserRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class DataInitializer {
    private final PostRepository postRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private static final String BIOGRAPHY = "Biography";
    private static final String BIOGRAPHY_COLOR = "yellow";
    private static final String HISTORY = "History";
    private static final String HISTORY_COLOR = "red";

    @EventListener(value = ApplicationReadyEvent.class)
    public void init() {
        log.info("start data initialization  ...");
        initPosts();
        initCourses();
        initUsers();
    }

    private void initPosts() {
        postRepository.deleteAll()
                .log()
                .subscribe();

        var magda = Post.builder()
                .title("Magda")
                .courseId(BIOGRAPHY)
                .courseColor(BIOGRAPHY_COLOR)
                .content("content of Magda")
                .build();

        var vit = Post.builder()
                .title("Vit")
                .courseId(BIOGRAPHY)
                .courseColor(BIOGRAPHY_COLOR)
                .content("content of Vit")
                .build();

        var vorsa = Post.builder()
                .title("Vorša")
                .courseId(HISTORY)
                .courseColor(HISTORY_COLOR)
                .content("content of Vorša")
                .build();

        postRepository.saveAll(List.of(magda, vit, vorsa))
                .log()
                .subscribe();

    }

    private void initCourses() {
        courseRepository.deleteAll()
                .log()
                .subscribe();

        var posts = Objects.requireNonNull(postRepository.findAll()
                        .collectList()
                        .block())
                .stream()
                .collect(Collectors.groupingBy(Post::getCourseId));

        var bio = Course.builder()
                .title(BIOGRAPHY)
                .color(BIOGRAPHY_COLOR)
                .posts(posts.getOrDefault(BIOGRAPHY, List.of()))
                .build();

        var hi = Course.builder()
                .title(HISTORY)
                .color(HISTORY_COLOR)
                .posts(posts.getOrDefault(HISTORY, List.of()))
                .build();

        courseRepository.saveAll(List.of(bio, hi))
                .log()
                .subscribe();
    }

    private void initUsers() {
        userRepository.deleteAll()
                .thenMany(Flux.just("user", "admin")
                        .flatMap(username -> {
                            var roles = "admin".equals(username)
                                    ? List.of("ROLE_USER", "ROLE_ADMIN")
                                    : List.of("ROLE_USER");
                            var user = User.builder()
                                    .roles(roles)
                                    .username(username)
                                    .password(passwordEncoder.encode("password"))
                                    .email(username + "@skarb.lt")
                                    .build();
                            return userRepository.save(user);
                        }))
                .log()
                .subscribe();
    }
}
