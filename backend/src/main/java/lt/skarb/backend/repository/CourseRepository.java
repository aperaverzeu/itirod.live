package lt.skarb.backend.repository;

import lt.skarb.backend.model.entity.Course;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CourseRepository extends ReactiveMongoRepository<Course, String> {
    @Query("{'title': ?0}")
    Mono<Course> getCourseByTitle(String title);
}
