package lt.skarb.backend.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wonder {
    @Id
    private String id;

    private String courseColor;

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
