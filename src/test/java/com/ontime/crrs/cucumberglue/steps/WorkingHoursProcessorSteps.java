package com.ontime.crrs.cucumberglue.steps;

import com.ontime.crrs.business.workinghours.exception.NonWorkingHoursException;
import com.ontime.crrs.business.workinghours.processor.WorkingHoursProcessor;
import com.ontime.crrs.persistence.reservation.entity.ReservationEntity;
import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import com.ontime.crrs.persistence.restaurant.repository.RestaurantRepository;
import com.ontime.crrs.persistence.table.entity.TableEntity;
import com.ontime.crrs.persistence.table.repository.TableRepository;
import com.ontime.crrs.persistence.user.entity.UserEntity;
import com.ontime.crrs.persistence.user.repository.UserRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;

import static com.ontime.crrs.helper.RestaurantTestHelper.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
public class WorkingHoursProcessorSteps {

    @Autowired
    private WorkingHoursProcessor workingHoursProcessor;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TableRepository tableRepository;

    private ReservationEntity nonWorkingHoursReservation;
    private ReservationEntity workingHoursReservation;
    private ReservationEntity reservation;
    private RestaurantEntity restaurant;
    private UserEntity owner;
    private TableEntity table;


    @Before("@working-hours-processor")
    public void setUp() {
        log.info("*********** Working Hours Processor Test Context Set Up ***********");

        owner = userRepository.save(buildDefaultOwner());
        restaurant = restaurantRepository.save(buildDefaultEntityWithAddress("KFC", owner));
        table = tableRepository.save(getDefaultTable(restaurant));

        workingHoursReservation = getDefaultReservation(LocalTime.of(11, 0, 0),
                LocalTime.of(13, 0, 0), restaurant, table);
        nonWorkingHoursReservation = getDefaultReservation(LocalTime.of(20, 0, 0),
                LocalTime.of(22, 0, 0), restaurant, table);
    }

    @When("the restaurant is during working hours")
    public void whenDuringWorkingHours() {
        reservation = workingHoursReservation;
    }

    @When("restaurant is not during working hours")
    public void whenNotDuringWorkingHours() {
        reservation = nonWorkingHoursReservation;
    }

    @Then("working hours processor does not throw an exception")
    public void thenProcessorDoesNotThrow() {
        workingHoursProcessor.isDuringWorkingHours(getReservationModel(reservation));
    }

    @Then("working hours processor throws an exception")
    public void thenProcessorThrowsException() {
        assertThrows(NonWorkingHoursException.class, () -> {
            workingHoursProcessor.isDuringWorkingHours(getReservationModel(reservation));
        });
    }

}