package ru.flint.interview.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import ru.flint.interview.util.annotation.NoHtml;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "questions")
public class Question extends AbstractEntity{
    @NoHtml
    @NotBlank
    @Size(min = 10,max = 100)
    private String question;
    @NoHtml
    @NotBlank
    @Size(min = 20,max = 1000)
    private String answer;
    @NoHtml
    @NotBlank
    @Size(min = 3,max = 30)
    private String topic;
    @NoHtml
    @NotBlank
    @Size(min = 3,max = 30)
    private String subtopic;
}
