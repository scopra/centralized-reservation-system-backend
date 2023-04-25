package com.ontime.crrs.cucumberglue.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CucumberHooks {

    @Before
    public void beforeCallingScenario() {
        log.info("*********** About to start the scenario ***********");
    }

    @After
    public void afterRunningScenario() {
        log.info("*********** Just finished running scenario ***********");
    }
}