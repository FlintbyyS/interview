package ru.flint.interview.web.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.flint.interview.entity.Question;
import ru.flint.interview.entity.Subtopic;
import ru.flint.interview.entity.Topic;
import ru.flint.interview.service.QuestionService;
import ru.flint.interview.service.TopicService;
import ru.flint.interview.web.dto.QuestionDTO;
import ru.flint.interview.web.mapper.QuestionMapper;


import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static ru.flint.interview.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = QuestionController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class QuestionController {
    public static final String REST_URL = "/api/version1.0/questions";
    private final QuestionService questionService;
    private final TopicService topicService;
    private final QuestionMapper mapper;

    public QuestionController(QuestionService questionService, TopicService topicService, QuestionMapper mapper) {
        this.questionService = questionService;
        this.topicService = topicService;
        this.mapper = mapper;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public List<QuestionDTO> getAll() {
        return questionService.findAll()
                .stream().map((mapper::toDTO)).collect(Collectors.toList());
    }

    @GetMapping("/topic/{id}")
    public List<QuestionDTO> getByTopic(@PathVariable long id) {
        return questionService.findByTopic(id)
                .stream().map((mapper::toDTO)).collect(Collectors.toList());
    }

    @GetMapping("/topics")
    public List<Topic> getTopics() {
        return topicService.findAll();
    }

    @GetMapping("/topic/subtopic")
    public List<QuestionDTO> getBySubtopic(@RequestParam long topic_id, @RequestParam long subtopic_id
            , @RequestParam(required = false) Long offset, @RequestParam(required = false) Long limit) {
        return questionService.findByTopicAndSubtopic(topic_id, subtopic_id, offset, limit)
                .stream().map((mapper::toDTO)).collect(Collectors.toList());
    }

    @GetMapping("/topic/subtopics")
    public List<Subtopic> getSubtopicsByTopic(@RequestParam long topic_id) {
        return topicService.findSubtopicsByTopic(topic_id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Question> createWithLocation(@Valid @RequestBody Question question) {
        checkNew(question);
        Question created = questionService.create(question);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        questionService.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Question update(@Valid @RequestBody Question question, @PathVariable long id) {
        return questionService.update(id, question);
    }
}
