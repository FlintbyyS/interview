package ru.flint.interview.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.flint.interview.util.annotation.NoHtml;
@Value
@EqualsAndHashCode(callSuper = true)
public class QuestionDTO extends AbstractDTO{
    @Positive
    Long id;
    @NoHtml
    @NotBlank
    @Size(min = 10,max = 100)
    String question;
    @NoHtml
    @NotBlank
    @Size(min = 20,max = 1000)
    String answer;
}
