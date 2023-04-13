package com.ontime.crrs;

import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@Slf4j
@SpringBootTest(
        webEnvironment = WebEnvironment.RANDOM_PORT
)
@CucumberContextConfiguration
public class CucumberSpringConfiguration {
//
//    @Test
//    void contextLoads() {
//    }
//
//    @Before
//    public void setUp() {
//        log.info("-------------- Spring Context Initialized For Executing Cucumber Tests --------------");
//    }
}