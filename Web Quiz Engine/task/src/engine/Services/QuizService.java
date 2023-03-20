package engine.Services;

import engine.Models.Quiz;
import engine.Repositories.QuizRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class QuizService {

    private final QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public void deleteById(Integer id) {
        this.quizRepository.deleteById(id);
    }

    public Page<Quiz> findAll (Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<Quiz> pagedResult = quizRepository.findAll(paging);

        return pagedResult;
    }

    public Optional<Quiz> findById(Integer id) {
        return this.quizRepository.findById(id);
    }

    public Quiz save(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }
}