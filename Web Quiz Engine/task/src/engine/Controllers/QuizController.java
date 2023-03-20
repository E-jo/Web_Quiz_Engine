package engine.Controllers;

import engine.Models.*;
import engine.Services.QuizService;
import engine.Services.SolvedService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.*;
import java.util.*;


@RestController
public class QuizController {

    @Autowired
    SolvedService solvedService;

    @Autowired
    QuizService quizService;

    @GetMapping("api/quizzes/{id}")
    public Quiz getQuiz(@PathVariable int id) {
        return quizService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("api/quizzes")
    public Page<Quiz> getAllQuizzes(@RequestParam(name = "page", defaultValue = "0") int page,
                                    @AuthenticationPrincipal User user ) {

        /*
        Quiz quiz = new Quiz();
        quiz.setUser(user);
        quiz.setTitle("The Java Logo");
        quiz.setText("What is depicted on the Java logo?");
        quiz.setOptions(new String[]{"Robot","Tea leaf","Cup of coffee","Bug"});
        quiz.setAnswer(new int[]{2});
        quizService.save(quiz);

         */


        return quizService.findAll(page, 10);
    }

    @GetMapping("api/quizzes/completed")
    public Page<Solved> getSolvedQuizzes(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                         @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                         @RequestParam(defaultValue = "completedAt") String sortBy,
                                         @AuthenticationPrincipal User user ) {


        Pageable pageable = PageRequest.of(page == null ? 0 : page, 10,
                Sort.by(sortBy).descending());
        return solvedService.findAll(page, pageSize, user);
    }

    @PostMapping("api/quizzes")
    public Quiz createQuiz(@Valid @RequestBody Quiz quiz, @AuthenticationPrincipal User user) {
        quiz.setUser(user);
        return quizService.save(quiz);
    }

    @PostMapping("api/quizzes/{id}/solve")
    //public Response solveQuiz(@PathVariable int id, @RequestParam int[] a, @AuthenticationPrincipal User user) {
    public Response solveQuiz(@PathVariable int id,
                              @RequestBody Answer answer,
                              @AuthenticationPrincipal User user) {
        Optional<Quiz> optionalQuiz = quizService.findById(id);

        //Answer answer = new Answer();
        //answer.setAnswer(new int[]{2});
        if (optionalQuiz.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (Arrays.equals(optionalQuiz.get().getAnswer(), answer.getAnswer())) {
            Solved solved = new Solved(optionalQuiz.get().getId());
            solved.setUserId(user.getId());
            solvedService.save(solved);

            return new Response(true);
        }

        return new Response(false);
    }

    @DeleteMapping("api/quizzes/{id}")
    public ResponseEntity<?> deleteQuiz(@PathVariable Integer id, @AuthenticationPrincipal User user) {
        Quiz quiz = quizService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (quiz.getUser().getId() == user.getId()) {
            quizService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

}