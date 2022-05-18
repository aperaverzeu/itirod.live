package lt.skarb.backend.controller;

import lombok.RequiredArgsConstructor;
import lt.skarb.backend.model.dto.CourseNameDTO;
import lt.skarb.backend.model.dto.PostDTO;
import lt.skarb.backend.model.entity.Post;
import lt.skarb.backend.model.mapper.PostMapper;
import lt.skarb.backend.service.PostService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping(value = "/api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final PostMapper postMapper;

    @GetMapping("")
    public Flux<Post> all() {
        return postService.all();
    }

    @GetMapping("/title")
    public Flux<Post> allByTitle(@RequestBody CourseNameDTO course) {
        return postService.allByTitle(course.courseName());
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<Post> create(@RequestBody PostDTO post) {
        return postService.create(postMapper.apply(post));
    }

    @GetMapping("/{id}")
    public Mono<Post> get(@PathVariable("id") String id) {
        return postService.get(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<Post> update(@PathVariable("id") String id,
                             @RequestBody PostDTO post) {
        return postService.update(id, postMapper.apply(post));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(NO_CONTENT)
    public Mono<Void> delete(@PathVariable("id") String id) {
        return postService.delete(id);
    }
}
