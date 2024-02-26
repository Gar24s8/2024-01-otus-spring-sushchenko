package ru.sushchenko.hw01;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sushchenko.hw01.service.TestRunnerService;

public class Application {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        var testRunnerService = context.getBean(TestRunnerService.class);
        testRunnerService.run();
    }
}
