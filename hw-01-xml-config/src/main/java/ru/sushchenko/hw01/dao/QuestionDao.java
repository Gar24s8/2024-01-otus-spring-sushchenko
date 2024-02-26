package ru.sushchenko.hw01.dao;

import ru.sushchenko.hw01.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll();
}
