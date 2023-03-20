package engine.Services;

import engine.Models.Quiz;
import engine.Models.Solved;
import engine.Models.User;
import engine.Repositories.QuizRepository;
import engine.Repositories.SolvedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SolvedService {
    private final SolvedRepository solvedRepository;

    @Autowired
    public SolvedService(SolvedRepository solvedRepository) {
        this.solvedRepository = solvedRepository;
    }

    public void deleteById(Integer id) {
        this.solvedRepository.deleteById(id);
    }

    public Page<Solved> findAll (Integer pageNo, Integer pageSize, User user) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("completedAt").descending());

        return solvedRepository.findAllByUserId(user.getId(), paging);
    }

    public Optional<Solved> findById(Integer id) {
        return this.solvedRepository.findById(id);
    }

    public Solved save(Solved solved) {
        return this.solvedRepository.save(solved);
    }

}

