package lt.skarb.backend.model.mapper;

import lt.skarb.backend.model.dto.PostDTO;
import lt.skarb.backend.model.entity.Post;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PostMapper implements Function<PostDTO, Post> {
    @Override
    public Post apply(PostDTO postDTO) {
        return Post.builder()
                .id(postDTO.id())
                .courseId(postDTO.courseId())
                .title(postDTO.title())
                .content(postDTO.content())
                .build();
    }
}
