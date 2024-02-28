package ru.sushchenko.hw02.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.sushchenko.hw02.dao.QuestionDao;
import ru.sushchenko.hw02.domain.Answer;
import ru.sushchenko.hw02.domain.Question;
import ru.sushchenko.hw02.domain.Student;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class TestServiceImplTest {

    @Mock
    private IOService ioService;

    @Mock
    private QuestionDao questionDao;

    @InjectMocks
    TestServiceImpl testService;

    private Student getStudent() {
        return new Student("Name", "Surname");
    }

    @BeforeEach
    public void init() {
        openMocks(this);
    }

    @Test
    void executeTestFor_ShouldReturnTestResultWithCorrectAnswers_WhenAllAnswersAreCorrect() {

        var expectedCorrectAnswers = 3;

        when(questionDao.findAll()).thenReturn(getQuestions());
        when(ioService.readIntForRange(anyInt(), anyInt(), anyString())).thenReturn(1);

        var testResult = testService.executeTestFor(getStudent());
        var actualCorrectAnswers = testResult.getRightAnswersCount();

        assertEquals(expectedCorrectAnswers, actualCorrectAnswers);
    }

    private List<Question> getQuestions() {
        var questionFirst = new Question("Question1",
                Arrays.asList(new Answer("1", true), new Answer("2", false)));
        var questionSecond = new Question("Question2",
                Arrays.asList(new Answer("1", true), new Answer("2", false)));
        var questionThird = new Question("Question3",
                Arrays.asList(new Answer("1", true), new Answer("2", false)));
        return Arrays.asList(questionFirst, questionSecond, questionThird);
    }
}