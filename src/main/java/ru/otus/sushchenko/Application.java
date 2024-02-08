package ru.otus.sushchenko;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.sushchenko.service.TestRunnerService;

public class Application {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        var testRunnerService = context.getBean(TestRunnerService.class);
        testRunnerService.run();

    }
}
