package ru.otus.sushchenko.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import ru.otus.sushchenko.dao.QuestionDao;
import ru.otus.sushchenko.domain.Answer;
import ru.otus.sushchenko.domain.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class TestServiceImplTest {

    @Mock
    private IOService ioServiceMock;
    @Mock
    private QuestionDao questionDaoMock;
    @Spy
    @InjectMocks
    TestServiceImpl testService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    private List<Question> createQuestionWithAnswers() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Question 1", Arrays.asList(new Answer("Answer 1", true)
                , new Answer("Answer 2", false))));
        questions.add(new Question("Question 2", Arrays.asList(new Answer("Answer 3", true)
                , new Answer("Answer 4", false))));
        return questions;
    }

    @Test
    void executeTest_ShouldPrintQuestionAndAnswers_WhenNotEmpty() {
        List<Question> questions = createQuestionWithAnswers();
        when(questionDaoMock.findAll()).thenReturn(questions);

        testService.executeTest();

        verify(ioServiceMock, times(1)).printFormattedLine("Please answer the questions below%n");
        verify(ioServiceMock, times(3)).printLine(anyString());
        verify(ioServiceMock, times(4)).printFormattedLine(anyString(), anyInt(), anyString(), anyBoolean());
    }
}