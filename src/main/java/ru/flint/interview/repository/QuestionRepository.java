package ru.flint.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.flint.interview.entity.Question;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface QuestionRepository extends JpaRepository<Question,Long> {
    List<Question> findByTopic_Id(long topic_id);

}
