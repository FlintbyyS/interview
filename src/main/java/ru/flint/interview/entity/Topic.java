package ru.flint.interview.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import ru.flint.interview.util.annotation.NoHtml;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "topics")
public class Topic extends AbstractEntity{

    @NoHtml
    @NotBlank
    @Size(min = 3,max = 30)
    private String name;

    @NotEmpty
    @OneToMany(fetch = FetchType.EAGER)
    @CollectionTable(name = "topic_subtopic",joinColumns = @JoinColumn(name = "topic_id"))
    @ToString.Exclude
    private List<Subtopic> subtopics;
}
