package ru.otus.sushchenko.dao;

import ru.otus.sushchenko.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll();
}
