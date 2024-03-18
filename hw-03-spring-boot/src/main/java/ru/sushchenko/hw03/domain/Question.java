package ru.sushchenko.hw03.domain;

import java.util.List;

public record Question(String text, List<Answer> answers) {
}
