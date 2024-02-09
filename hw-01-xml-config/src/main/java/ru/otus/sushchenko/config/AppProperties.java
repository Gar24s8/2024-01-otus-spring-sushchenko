package ru.otus.sushchenko.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AppProperties implements TestFileNameProvider {
    private String testFileName;

    public String getTestFileName() {
        return testFileName;
    }
}
