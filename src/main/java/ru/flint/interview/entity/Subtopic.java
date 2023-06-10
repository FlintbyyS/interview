package ru.flint.interview.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import ru.flint.interview.util.annotation.NoHtml;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "subtopics")
public class Subtopic extends AbstractEntity {

    @NoHtml
    @NotEmpty
    @Size(min = 3, max = 30)
    private String name;
}
