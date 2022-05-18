package lt.skarb.backend.model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Wonder extends Post {
    @Id
    private String id;

    private String courseColor;

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
