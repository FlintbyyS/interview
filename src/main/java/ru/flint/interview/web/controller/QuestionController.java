package ru.flint.interview.web.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.flint.interview.entity.Question;
import ru.flint.interview.service.QuestionService;


import java.net.URI;
import java.util.List;

import static ru.flint.interview.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = QuestionController.REST_URL,produces = MediaType.APPLICATION_JSON_VALUE)
public class QuestionController {
    public static final String REST_URL = "/api/version1.0/questions";
    private final QuestionService service;

    public QuestionController(QuestionService service) {
        this.service = service;
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public List<Question> getAll(){
        return service.findAll();
    }
    @GetMapping("/topic")
    public List<Question> getByTopic(@RequestParam String topic){
        return service.findByTopicIgnoreCase(topic);
    }
    @GetMapping("/topics")
    public List<String> getTopics(){
        return service.findTopics();
    }
    @GetMapping("/subtopic")
    public List<Question> getBySubtopic(@RequestParam String subtopic){
        return service.findBySubtopicIgnoreCase(subtopic);
    }
    @GetMapping("/subtopics")
    public List<String> getSubtopicsByTopic(@RequestParam String topic){
        return service.findSubtopicsByTopic(topic);
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Question> createWithLocation(@Valid @RequestBody Question question) {
        checkNew(question);
        Question created = service.create(question);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Question update(@Valid @RequestBody Question question, @PathVariable long id) {
        return service.update(id,question);
    }
}
