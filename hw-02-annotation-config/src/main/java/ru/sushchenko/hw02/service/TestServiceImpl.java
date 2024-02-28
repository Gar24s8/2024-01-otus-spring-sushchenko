package ru.sushchenko.hw02.service;

import lombok.RequiredArgsConstructor;
import ru.sushchenko.hw02.dao.QuestionDao;
import ru.sushchenko.hw02.domain.Answer;
import ru.sushchenko.hw02.domain.Student;
import ru.sushchenko.hw02.domain.TestResult;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    private final QuestionDao questionDao;

    @Override
    public TestResult executeTestFor(Student student) {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");
        var questions = questionDao.findAll();
        var testResult = new TestResult(student);

        for (var question : questions) {
            ioService.printFormattedLine("%n %s", question.text());
            int numberAnswer = 1;
            for (Answer answer : question.answers()) {
                ioService.printFormattedLine("%d: %s", numberAnswer, answer.text());
                numberAnswer++;
            }
            int studentAnswer = ioService.readIntForRange(1, question.answers().size(),
                    "You print incorrect number of answer, try again");
            boolean isAnswerValid = question.answers().get(studentAnswer - 1).isCorrect();
            testResult.applyAnswer(question, isAnswerValid);
        }
        return testResult;
    }
}
