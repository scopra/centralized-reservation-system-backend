package com.ontime.crrs.cucumberglue.steps;

import com.ontime.crrs.business.workinghours.exception.NonWorkingHoursException;
import com.ontime.crrs.business.workinghours.processor.WorkingHoursProcessor;
import com.ontime.crrs.persistence.restaurant.repository.RestaurantRepository;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;

import static com.ontime.crrs.helper.RestaurantTestHelper.EXISTING_RESTAURANT;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
public class WorkingHoursProcessorSteps {

    @Autowired
    private WorkingHoursProcessor workingHoursProcessor;

    @Autowired
    private RestaurantRepository restaurantRepository;

    private final LocalTime duringWorkingHours = LocalTime.of(12, 0, 0);
    private final LocalTime notDuringWorkingHours = LocalTime.of(3, 0, 0);
    private final String RESTAURANT_NAME = EXISTING_RESTAURANT.getName();
    private LocalTime reservationTime;

    @Before("@working-hours-processor")
    public void setUp() {
        log.info("*********** Working Hours Processor Test Context Set Up ***********");
        restaurantRepository.save(EXISTING_RESTAURANT);
    }

    @After("@working-hours-processor")
    public void tearDown() {
        log.info("*********** Working Hours Processor Test Context Tear Down ***********");
        restaurantRepository.deleteAll();
    }

    @When("the restaurant is during working hours")
    public void whenDuringWorkingHours() {
        reservationTime = duringWorkingHours;
    }

    @When("restaurant is not during working hours")
    public void whenNotDuringWorkingHours() {
        reservationTime = notDuringWorkingHours;
    }

    @Then("working hours processor returns true")
    public void thenProcessorReturnsTrue() {
        var flag = workingHoursProcessor.isDuringWorkingHours(RESTAURANT_NAME, reservationTime);
        assertThat(flag).isTrue();
    }

    @Then("working hours processor throws an exception")
    public void thenProcessorThrowsException() {
        assertThrows(NonWorkingHoursException.class, () -> {
            workingHoursProcessor.isDuringWorkingHours(RESTAURANT_NAME, reservationTime);
        });
    }

}