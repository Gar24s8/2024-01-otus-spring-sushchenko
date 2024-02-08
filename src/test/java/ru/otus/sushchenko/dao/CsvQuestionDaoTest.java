package ru.otus.sushchenko.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import ru.otus.sushchenko.config.TestFileNameProvider;
import ru.otus.sushchenko.exceptions.QuestionReadException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
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
        when(fileNameProvider.getTestFileName()).thenReturn("questions.csv");
        int actualRows = csvQuestionDao.findAll().size();

        assertEquals(expectedRows, actualRows);
    }

    @Test
    void findAll_ShouldThrowQuestionReadException_WhenCsvFileIsInvalid() {
        when(fileNameProvider.getTestFileName()).thenReturn("invalid.csv");

        assertThrowsExactly(QuestionReadException.class, () -> csvQuestionDao.findAll(), "CSV file not found");
    }

}