//TODO: Fix after reservation is merged
//package com.ontime.crrs.cucumberglue.steps;
//
//import com.ontime.crrs.business.workinghours.exception.NonWorkingHoursException;
//import com.ontime.crrs.business.workinghours.processor.WorkingHoursProcessor;
//import com.ontime.crrs.helper.Reservation;
//import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
//import com.ontime.crrs.persistence.restaurant.repository.RestaurantRepository;
//import com.ontime.crrs.persistence.user.entity.UserEntity;
//import com.ontime.crrs.persistence.user.repository.UserRepository;
//import io.cucumber.java.After;
//import io.cucumber.java.Before;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.time.LocalTime;
//
//import static com.ontime.crrs.helper.RestaurantTestHelper.*;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@Slf4j
//public class WorkingHoursProcessorSteps {
//
//    @Autowired
//    private WorkingHoursProcessor workingHoursProcessor;
//
//    @Autowired
//    private RestaurantRepository restaurantRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    private Reservation nonWorkingHoursReservation;
//    private Reservation workingHoursReservation;
//    private RestaurantEntity restaurant = null;
//    private UserEntity owner;
//    private Reservation reservation;
//
//
//    @Before("@working-hours-processor")
//    public void setUp() {
//        log.info("*********** Working Hours Processor Test Context Set Up ***********");
//
//        owner = userRepository.save(buildDefaultOwner());
//
//        restaurant = restaurantRepository.save(buildDefaultEntityWithAddress("KFC", owner));
//
//        workingHoursReservation = getDefaultReservation(LocalTime.of(11, 0, 0),
//                LocalTime.of(13, 0, 0), restaurant);
//        nonWorkingHoursReservation = getDefaultReservation(LocalTime.of(20, 0, 0),
//                LocalTime.of(22, 0, 0), restaurant);
//    }
//
//    @After("@working-hours-processor")
//    public void tearDown() {
//        log.info("*********** Working Hours Processor Test Context Tear Down ***********");
//        userRepository.deleteAll();
//        restaurantRepository.deleteAll();
//    }
//
//    @When("the restaurant is during working hours")
//    public void whenDuringWorkingHours() {
//        reservation = workingHoursReservation;
//    }
//
//    @When("restaurant is not during working hours")
//    public void whenNotDuringWorkingHours() {
//        reservation = nonWorkingHoursReservation;
//    }
//
//    @Then("working hours processor returns true")
//    public void thenProcessorReturnsTrue() {
//        var flag = workingHoursProcessor.isDuringWorkingHours(reservation);
//        assertThat(flag).isTrue();
//    }
//
//    @Then("working hours processor throws an exception")
//    public void thenProcessorThrowsException() {
//        assertThrows(NonWorkingHoursException.class, () -> {
//            workingHoursProcessor.isDuringWorkingHours(reservation);
//        });
//    }
//
//}