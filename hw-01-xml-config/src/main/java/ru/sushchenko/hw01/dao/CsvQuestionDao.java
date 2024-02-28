package ru.sushchenko.hw01.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import ru.sushchenko.hw01.config.TestFileNameProvider;
import ru.sushchenko.hw01.dao.dto.QuestionDto;
import ru.sushchenko.hw01.domain.Question;
import ru.sushchenko.hw01.exceptions.QuestionReadException;

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
