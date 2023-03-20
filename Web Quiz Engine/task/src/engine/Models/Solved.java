package engine.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Solved {

    @JsonProperty
    int id;

    @JsonProperty
    @Id
    LocalDateTime completedAt;

    @JsonIgnore
    private int userId;

    public Solved(int quizId) {
        this.id = quizId;
        this.completedAt = LocalDateTime.now();
    }

    Solved() {
        this.completedAt = LocalDateTime.now();
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getUserId() {
        return this.userId;
    }
    public void setId(int quizId) {
        this.id = quizId;
    }
}
