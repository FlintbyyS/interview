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

    public List<Question> findByTopicIgnoreCase(String topic) {
        log.info("Find question with topic = {}",topic);
        return repository.findByTopicIgnoreCase(topic);
    }

    public List<String> findTopics() {
        log.info("Find topics");
        return repository.findTopics();
    }

    public List<Question> findBySubtopicIgnoreCase(String subtopic) {
        log.info("Find question with subtopic = {}",subtopic);
        return repository.findBySubtopicIgnoreCase(subtopic);
    }

    public List<String> findSubtopicsByTopic(String topic) {
        log.info("Find subtopics with topic = {}",topic);
        return repository.findSubtopicsByTopic(topic);
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
