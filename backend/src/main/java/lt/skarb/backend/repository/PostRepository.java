package lt.skarb.backend.repository;

import lt.skarb.backend.model.entity.Post;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PostRepository extends ReactiveMongoRepository<Post, String> {
    @Query("{ 'courseId' : ?0 }")
    Flux<Post> getPostsByCourseId(String courseId);

    @Query("{ 'title' : ?0 }")
    Mono<Post> getPostByTitle(String title);
}
