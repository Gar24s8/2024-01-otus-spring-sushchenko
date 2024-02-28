package ru.sushchenko.hw02.dao;

import ru.sushchenko.hw02.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll();
}
