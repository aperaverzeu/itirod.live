package lt.skarb.backend.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    private String id;

    private String courseId;

    private String courseColor;

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
