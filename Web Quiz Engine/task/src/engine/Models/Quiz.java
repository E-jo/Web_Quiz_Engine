package engine.Models;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@Table(name = "quizzes")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Title must not be empty")
    private String title;

    @NotBlank(message = "Text must not be empty")
    private String text;

    @NotNull
    @Size(min = 2, message = "Please provide two or more answer options")
    private String[] options;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int[] answer;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn
    private User user;

    public Quiz() {
        this.answer = new int[]{};
    }

    public Quiz(String title, String text, String[] options, int[] answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }
}