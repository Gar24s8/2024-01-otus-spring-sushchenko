package ru.sushchenko.hw02.domain;

import java.util.List;

public record Question(String text, List<Answer> answers) {
}
