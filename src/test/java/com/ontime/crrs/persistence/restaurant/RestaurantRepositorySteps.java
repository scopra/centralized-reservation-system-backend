package com.ontime.crrs.persistence.restaurant;

import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import com.ontime.crrs.persistence.restaurant.repository.RestaurantRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import static com.ontime.crrs.helper.RestaurantTestHelper.buildDefaultEntityWithName;
import static org.junit.jupiter.api.Assertions.*;

public class RestaurantRepositorySteps {

    @Autowired
    private RestaurantRepository repository;
    private String restaurantName;
    private RestaurantEntity foundEntity;
    private Integer foundID;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        if (foundEntity != null) {
            repository.delete(foundEntity);
        }
    }

    @Given("there is a restaurant with name {string} in the database")
    public void givenRestaurantExists(String name) {
        repository.save(buildDefaultEntityWithName(name));
        restaurantName = name;
    }

    @Given("there is no restaurant with name {string} in the database")
    public void givenRestaurantDoesNotExistWithName(String name) {

    }

    @When("I search for a restaurant by name using {string}")
    public void whenSearchRestaurantByName(String name) {
        foundEntity = repository.findRestaurantByName(name).orElse(null);
    }

    @When("I search for a restaurant ID by name using {string}")
    public void whenSearchForRestaurantIdByName(String name) {
        foundID = repository.findRestaurantIdByName(name);
    }

    @Then("the method should return the restaurant entity for the given name")
    public void thenShouldReturnRestaurantEntity() {
        assertNotNull(foundEntity);
        assertEquals(restaurantName, foundEntity.getName());
    }

    @Then("the method should return null")
    public void thenShouldReturnEmptyOptional() {
        assertNull(foundEntity);
    }

    @Then("the method should return null ID")
    public void thenShouldReturnNull() {
        assertNull(foundID);
    }

    @Then("the method should return the ID of the restaurant for the given name")
    public void thenShouldReturnCorrectIdForGivenName() {
        assertNotNull(foundID);
        assertEquals(foundID, foundEntity.getId());
    }

}