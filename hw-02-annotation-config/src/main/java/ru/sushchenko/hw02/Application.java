package ru.sushchenko.hw02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sushchenko.hw02.config.AppConfig;
import ru.sushchenko.hw02.service.TestRunnerService;

public class Application {
    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(AppConfig.class);
        var testRunnerService = context.getBean(TestRunnerService.class);
        testRunnerService.run();

    }
}