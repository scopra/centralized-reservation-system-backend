package com.ontime.crrs.cucumberglue.hooks;

import com.ontime.crrs.persistence.location.repository.LocationRepository;
import com.ontime.crrs.persistence.restaurant.repository.RestaurantRepository;
import com.ontime.crrs.persistence.rule.repository.RuleRepository;
import com.ontime.crrs.persistence.table.repository.TableRepository;
import com.ontime.crrs.persistence.user.repository.UserRepository;
import com.ontime.crrs.persistence.workinghours.repository.WorkingHoursRepository;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CucumberHooks {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private WorkingHoursRepository workingHoursRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private RuleRepository ruleRepository;

    @Before
    public void beforeCallingScenarios() {
        log.info("*********** About to start the scenario ***********");

    }

    @After
    public void afterRunningScenarios() {
        log.info("*********** Just finished running scenario ***********");
        restaurantRepository.deleteAll();
        userRepository.deleteAll();
        tableRepository.deleteAll();
        workingHoursRepository.deleteAll();
        locationRepository.deleteAll();
        ruleRepository.deleteAll();
    }

}