package ru.sushchenko.hw02.service;

import ru.sushchenko.hw02.domain.Student;
import ru.sushchenko.hw02.domain.TestResult;

public interface TestService {
    TestResult executeTestFor(Student student);
}
