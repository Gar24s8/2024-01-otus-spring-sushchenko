package ru.sushchenko.hw03.dao;

import ru.sushchenko.hw03.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll();
}
