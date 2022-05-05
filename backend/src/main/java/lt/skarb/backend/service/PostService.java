package lt.skarb.backend.service;

import lt.skarb.backend.model.entity.Post;
import lt.skarb.backend.repository.PostRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public record PostService(PostRepository postRepository) {
    public Flux<Post> all() {
        return this.postRepository.findAll();
    }

    public Mono<Post> create(Post post) {
        return this.postRepository.save(post);
    }

    public Mono<Post> get(String id) {
        return this.postRepository.findById(id);
    }

    public Mono<Post> update(String id, Post post) {
        return this.postRepository.findById(id)
                .map(p -> {
                    p.setTitle(post.getTitle());
                    p.setContent(post.getContent());
                    return p;
                })
                .flatMap(this.postRepository::save);
    }

    public Mono<Void> delete(String id) {
        return this.postRepository.deleteById(id);
    }
}
