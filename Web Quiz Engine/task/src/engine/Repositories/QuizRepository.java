package engine.Repositories;

import engine.Models.Quiz;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.*;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends PagingAndSortingRepository <Quiz, Integer> {
    void deleteById(Integer id);
    List<Quiz> findAll();
    Optional<Quiz> findById(Integer id);
    Quiz save(Quiz quiz);
}