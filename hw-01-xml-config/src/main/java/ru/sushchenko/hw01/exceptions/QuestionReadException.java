package ru.sushchenko.hw01.exceptions;

public class QuestionReadException extends RuntimeException {
    public QuestionReadException(String message, Throwable ex) {
        super(message, ex);
    }
}
