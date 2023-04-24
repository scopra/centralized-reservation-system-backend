package com.ontime.crrs.cucumberglue.steps;

import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import com.ontime.crrs.persistence.restaurant.repository.RestaurantRepository;
import com.ontime.crrs.persistence.restaurant.service.RestaurantService;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.ontime.crrs.helper.RestaurantTestHelper.*;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class RestaurantServiceSteps {

    private final RestaurantEntity updatedRestaurant = buildCustomRestaurantEntity(RESTAURANT_NAME, RESTAURANT_ADDRESS,
            RESTAURANT_MUNICIPALITY, RESTAURANT_CITY);
    private final RestaurantEntity restaurant1 = buildCustomRestaurantEntity(NAME_1, ADDRESS_1,
            RESTAURANT_MUNICIPALITY, RESTAURANT_CITY);
    private final RestaurantEntity restaurant2 = buildCustomRestaurantEntity(NAME_2, ADDRESS_2,
            RESTAURANT_MUNICIPALITY, RESTAURANT_CITY);
    private final RestaurantEntity existingRestaurant = preBuildRestaurantEntity();

    @Autowired
    private RestaurantService service;

    @Autowired
    private RestaurantRepository repository;

    private List<RestaurantEntity> savedRestaurants;
    private List<RestaurantEntity> addedRestaurants;
    private UUID foundID;
    private RestaurantEntity foundEntity;

    @Before("@restaurant-service")
    public void setUp() {
        log.info("*********** Restaurant Service Test Context Set Up ***********");

        addedRestaurants = new ArrayList<RestaurantEntity>();
        savedRestaurants = new ArrayList<RestaurantEntity>();
    }

    @After("@restaurant-service")
    public void tearDown() {
        log.info("*********** Restaurant Service Test Context Tear Down ***********");

        repository.deleteAll();
        foundID = null;
        foundEntity = null;
    }

    @Given("the restaurant already exists in the database")
    public void givenRestaurantAlreadyExists() {
        repository.save(existingRestaurant);
    }

    @Given("the restaurant does not exist in the database")
    public void givenRestaurantDoesNotExist() {
        assertThat(repository.findRestaurantByName(existingRestaurant.getName())).isEmpty();
    }

    @Given("more than one restaurant exists in the database")
    public void givenMoreThanOneRestaurantInDatabase() {
        addedRestaurants.add(repository.save(restaurant1));
        addedRestaurants.add(repository.save(restaurant2));

        assertThat(repository.count()).isGreaterThan(1);
    }

    @Given("the database is empty")
    public void givenDatabaseEmpty() {
        assertThat(repository.count()).isEqualTo(0);
    }

    @When("I update the restaurant")
    public void whenRestaurantIsUpdated() {
        service.updateRestaurant(updatedRestaurant);
    }

    @When("I search for all restaurants")
    public void whenSearchForAllRestaurants() {
        savedRestaurants = service.findAllRestaurants();
    }

    @When("I search for restaurant ID by name {string}")
    public void whenSearchForRestaurantIDByName(String name) {
        foundID = service.findRestaurantIdByName(name);
    }

    @When("I search for restaurant by ID")
    public void whenSearchForRestaurantByID() {
        var savedEntityID = repository.findRestaurantIdByName(existingRestaurant.getName());

        foundEntity = service.findRestaurantById(savedEntityID);
    }

    @Then("the restaurant information is updated successfully")
    public void thenRestaurantUpdated() {
        assertThat(repository.findRestaurantByName(RESTAURANT_NAME)).isPresent();

        assertThat(repository.findRestaurantByName(RESTAURANT_NAME).get()).isEqualTo(updatedRestaurant);
    }

    @Then("the list of all restaurants is returned")
    public void thenListOfAllRestaurantsIsReturned() {
        assertThat(savedRestaurants).hasSize(addedRestaurants.size());

        assertThat(savedRestaurants).containsExactlyInAnyOrderElementsOf(addedRestaurants);
    }

    @Then("the returned list of restaurants is empty")
    public void thenReturnedListIsEmpty() {
        assertThat(savedRestaurants.size()).isEqualTo(0);
    }

    @Then("the restaurant with correct ID is found")
    public void thenCorrectRestaurantIsFound() {
        assertThat(foundID).isNotNull();

        assertThat(repository.findById(foundID)).isPresent();

        assertThat(existingRestaurant.getId()).isEqualTo(repository.findById(foundID).get().getId());
    }

    @Then("the findRestaurantIdByName method should return null")
    public void thenFindRestaurantIdByNameReturnsNull() {
        assertThat(foundID).isNull();
    }

    @Then("the restaurant entity is returned")
    public void thenReturnsCorrectRestaurantEntity() {
        assertThat(foundEntity).isNotNull();

        assertThat(foundEntity).isEqualTo(existingRestaurant);
    }

}
