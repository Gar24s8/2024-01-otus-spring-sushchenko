package ru.sushchenko.hw03.service;

import ru.sushchenko.hw03.domain.Student;
import ru.sushchenko.hw03.domain.TestResult;

public interface TestService {
    TestResult executeTestFor(Student student);


}
