package ru.flint.interview.web.mapper;

import org.springframework.stereotype.Component;
import ru.flint.interview.entity.Question;
import ru.flint.interview.web.dto.QuestionDTO;

@Component
public class QuestionMapper implements Mapper<Question, QuestionDTO>{
    @Override
    public Question toEntity(QuestionDTO dto) {
        return null;
    }

    @Override
    public QuestionDTO toDTO(Question entity) {
        return new QuestionDTO(entity.getId(),entity.getQuestion(),entity.getAnswer()
                ,entity.getTopic().getId(),entity.getTopic().getName()
                ,entity.getSubtopic().getId(),entity.getSubtopic().getName());
    }
}
