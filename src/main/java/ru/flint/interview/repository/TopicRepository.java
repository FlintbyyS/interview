package ru.flint.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.flint.interview.entity.Topic;


@Repository
@Transactional(readOnly = true)
public interface TopicRepository extends JpaRepository<Topic,Long> {
}
