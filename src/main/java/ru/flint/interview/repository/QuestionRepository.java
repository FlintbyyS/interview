package ru.flint.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.flint.interview.entity.Question;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface QuestionRepository extends JpaRepository<Question,Long> {
    List<Question> findByTopicIgnoreCase(String topic);
    List<Question> findBySubtopicIgnoreCase(String topic);
    @Query("SELECT distinct q.subtopic from questions q where q.topic =:topic")
    List<String> findSubtopicsByTopic(String topic);

    @Query("select distinct q.topic from questions q")
    List<String> findTopics();
}
