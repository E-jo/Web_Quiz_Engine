package engine.Repositories;

import engine.Models.Quiz;
import engine.Models.Solved;
import engine.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolvedRepository extends JpaRepository<Solved, Integer> {
    Page<Solved> findAllByUserId(int userId, Pageable pageable);
    Solved save(Solved solved);
}