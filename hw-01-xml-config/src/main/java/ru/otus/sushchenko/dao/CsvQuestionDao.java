package ru.otus.sushchenko.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import ru.otus.sushchenko.config.TestFileNameProvider;
import ru.otus.sushchenko.dao.dto.QuestionDto;
import ru.otus.sushchenko.domain.Question;
import ru.otus.sushchenko.exceptions.QuestionReadException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@RequiredArgsConstructor
public class CsvQuestionDao implements QuestionDao {

    private final TestFileNameProvider fileNameProvider;

    @Override
    public List<Question> findAll() {
        try (var inputStream = requireNonNull(getClass().getClassLoader()
                .getResourceAsStream(fileNameProvider.getTestFileName()), "Error reading CSV file")) {
            return processCsvFile(inputStream);
        } catch (Exception e) {
            throw new QuestionReadException("Error reading CSV file", e);
        }
    }

    private List<Question> processCsvFile(InputStream inputStream) {
        var reader = new InputStreamReader(inputStream);
        var csvReader = new CsvToBeanBuilder<QuestionDto>(reader)
                .withType(QuestionDto.class)
                .withSeparator(';')
                .withIgnoreLeadingWhiteSpace(true)
                .withIgnoreEmptyLine(true)
                .withSkipLines(1)
                .build();
        return csvReader.parse().stream()
                .map(QuestionDto::toDomainObject)
                .collect(Collectors.toList());
    }
}
