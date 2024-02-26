package ru.sushchenko.hw01.service;

import lombok.RequiredArgsConstructor;
import ru.sushchenko.hw01.dao.QuestionDao;
import ru.sushchenko.hw01.domain.Answer;
import ru.sushchenko.hw01.domain.Question;

import java.util.List;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    private final QuestionDao questionDao;

    public void executeTest() {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");
        List<Question> questions = questionDao.findAll();
        for (Question question : questions) {
            ioService.printLine("\n" + question.text());
            List<Answer> answers = question.answers();
            int answerNumber = 1;
            for (Answer answer : answers) {
                ioService.printFormattedLine("%d: %s - %s", answerNumber, answer.text(), answer.isCorrect());
                answerNumber++;
            }
        }
    }
}
