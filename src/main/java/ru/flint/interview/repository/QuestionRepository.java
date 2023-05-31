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
    List<Question> findByTopic_Id(long topic_id);

    @Query("select q from questions q where q.topic.id =:topic_id and q.subtopic.id =:subtopic_id")
    List<Question> findByTopic_IdAndSubtopic_Id(long topic_id,long subtopic_id);
}
