package ru.sushchenko.hw02.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.sushchenko.hw02.dao.CsvQuestionDao;
import ru.sushchenko.hw02.dao.QuestionDao;
import ru.sushchenko.hw02.service.IOService;
import ru.sushchenko.hw02.service.ResultService;
import ru.sushchenko.hw02.service.ResultServiceImpl;
import ru.sushchenko.hw02.service.StreamsIOService;
import ru.sushchenko.hw02.service.StudentService;
import ru.sushchenko.hw02.service.StudentServiceImpl;
import ru.sushchenko.hw02.service.TestRunnerService;
import ru.sushchenko.hw02.service.TestRunnerServiceImpl;
import ru.sushchenko.hw02.service.TestService;
import ru.sushchenko.hw02.service.TestServiceImpl;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Bean
    public TestRunnerService runnerService(TestService testService, StudentService studentService,
                                           ResultService resultService) {
        return new TestRunnerServiceImpl(testService, studentService, resultService);
    }

    @Bean
    public AppProperties appProperties() {
        return new AppProperties();
    }

    @Bean
    public ResultService resultService(TestConfig testConfig, IOService ioService) {
        return new ResultServiceImpl(testConfig, ioService);
    }

    @Bean
    public TestConfig testConfig() {
        return new AppProperties();
    }

    @Bean
    public IOService ioService() {
        return new StreamsIOService(java.lang.System.out, java.lang.System.in);
    }

    @Bean
    public StudentService studentService(IOService ioService) {
        return new StudentServiceImpl(ioService);
    }

    @Bean
    public TestService testService(IOService ioService, QuestionDao questionDao) {
        return new TestServiceImpl(ioService, questionDao);
    }

    @Bean
    public QuestionDao questionDao(TestFileNameProvider fileNameProvider) {
        return new CsvQuestionDao(fileNameProvider);
    }
}
