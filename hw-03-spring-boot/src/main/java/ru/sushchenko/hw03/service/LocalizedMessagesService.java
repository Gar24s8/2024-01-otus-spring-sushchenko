package ru.sushchenko.hw03.service;

public interface LocalizedMessagesService {
    String getMessage(String code, Object... args);
}
