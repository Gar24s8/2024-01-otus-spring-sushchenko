package ru.otus.sushchenko.service;

public interface IOService {
    void printLine(String s);

    void printFormattedLine(String s, Object ...args);
}