package ru.flint.interview.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import ru.flint.interview.util.annotation.NoHtml;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "questions")
public class Question extends AbstractEntity {
    @NoHtml
    @NotBlank
    @Size(min = 10, max = 100)
    private String question;

    @NoHtml
    @NotBlank
    @Size(min = 20, max = 1000)
    private String answer;

    @NotEmpty
    @OneToOne
    @ToString.Exclude
    private Topic topic;

    @NotEmpty
    @OneToOne
    @ToString.Exclude
    private Subtopic subtopic;
}
