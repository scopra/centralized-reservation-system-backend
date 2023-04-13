package com.ontime.crrs;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features/",
        glue = {"com.ontime.crrs.cucumberglue"},
        plugin = {"pretty", "html:build/reports/tests/cucumber/cucumber-report.html"}
)
public class CucumberIntegrationTest {
}