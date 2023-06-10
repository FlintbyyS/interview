package ru.flint.interview.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.flint.interview.entity.Subtopic;
import ru.flint.interview.entity.Topic;
import ru.flint.interview.repository.TopicRepository;

import java.util.List;

@Service
@Slf4j
public class TopicService {

    private final TopicRepository repository;

    public TopicService(TopicRepository repository) {
        this.repository = repository;
    }

    public List<Topic> findAll() {
        log.info("Find all topics");
        return repository.findAll();
    }

    public List<Subtopic> findSubtopicsByTopic(Long id) {
        log.info("Find subtopics with topic_id = {}", id);
        return repository.findById(id).isPresent() ? repository.findById(id).get().getSubtopics() : null;
    }
}
