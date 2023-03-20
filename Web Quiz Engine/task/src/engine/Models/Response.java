package engine.Models;

import lombok.Getter;

@Getter
public class Response {

    private static final String SUCCESS_TEXT = "Congratulations, you're right!";
    private static final String NON_SUCCESS_TEXT = "Wrong answer! Please, try again.";

    private final boolean success;
    private final String feedback;

    public Response(boolean success) {
        this.success = success;
        this.feedback = success ? SUCCESS_TEXT : NON_SUCCESS_TEXT;
    }

    public Response() {
        this.success = false;
        this.feedback = NON_SUCCESS_TEXT;
    }
}