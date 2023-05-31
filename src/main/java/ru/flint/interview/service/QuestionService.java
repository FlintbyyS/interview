package ru.flint.interview.service;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.flint.interview.entity.Question;
import ru.flint.interview.repository.QuestionRepository;

import java.util.List;


@Service
@Slf4j
public class QuestionService {

    private final QuestionRepository repository;

    public QuestionService(QuestionRepository repository) {
        this.repository = repository;
    }

    public Question create(@NotNull Question question) {
        log.info("Create question: {}",question);
        return repository.save(question);
    }

    public List<Question> findAll() {
        log.info("Find all questions");
        return repository.findAll();
    }

    public List<Question> findByTopic(long topic_id) {
        log.info("Find question with topic_id = {}",topic_id);
        return repository.findByTopic_Id(topic_id);
    }

    public List<Question> findByTopicAndSubtopic(long topic_id,long subtopic_id) {
        log.info("Find question with topic_id = {} and subtopic_id = {}",topic_id,subtopic_id);
        return repository.findByTopic_IdAndSubtopic_Id(topic_id,subtopic_id);
    }
    @Transactional
    public Question update(long id, Question question) {
        log.info("Update question with id = {}", question.getId());
        question.setId(id);
        return repository.save(question);
    }
    public void delete(long id) {
        log.info("Delete question with id = {}",id);
        repository.deleteById(id);
    }
}
