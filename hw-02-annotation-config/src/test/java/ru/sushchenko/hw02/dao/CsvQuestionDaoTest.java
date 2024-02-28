package ru.sushchenko.hw02.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import ru.sushchenko.hw02.config.TestFileNameProvider;
import ru.sushchenko.hw02.exceptions.QuestionReadException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CsvQuestionDaoTest {

    @Mock
    private TestFileNameProvider fileNameProvider;
    @Spy
    @InjectMocks
    private CsvQuestionDao csvQuestionDao;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_ShouldReturnExpectedSize_WhenExists() {
        int expectedRows = 5;
        when(fileNameProvider.getTestFileName()).thenReturn("testQuestions.csv");
        int actualRows = csvQuestionDao.findAll().size();

        assertEquals(expectedRows, actualRows);
    }

    @Test
    void findAll_ShouldThrowQuestionReadException_WhenCsvFileIsInvalid() {
        when(fileNameProvider.getTestFileName()).thenReturn("invalid.csv");

        assertThrows(QuestionReadException.class, () -> csvQuestionDao.findAll());
    }

}