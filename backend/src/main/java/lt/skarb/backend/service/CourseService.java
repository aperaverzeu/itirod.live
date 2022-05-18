package lt.skarb.backend.service;

import lt.skarb.backend.model.entity.Course;
import lt.skarb.backend.repository.CourseRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public record CourseService(CourseRepository courseRepository) {
    public Flux<Course> getCourses() {
        return courseRepository.findAll();
    }

    public Mono<String> getCourseColorByTitle(String title) {
        return courseRepository.getCourseByTitle(title)
                .map(Course::getColor)
                .switchIfEmpty(Mono.empty());
    }
}
