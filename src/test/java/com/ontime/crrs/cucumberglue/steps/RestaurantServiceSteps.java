package com.ontime.crrs.cucumberglue.steps;

import com.ontime.crrs.business.restaurant.exception.RestaurantNotFoundException;
import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import com.ontime.crrs.persistence.restaurant.repository.RestaurantRepository;
import com.ontime.crrs.persistence.restaurant.service.RestaurantService;
import com.ontime.crrs.persistence.user.repository.UserRepository;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
public class RestaurantServiceSteps {

    @Autowired
    private RestaurantService service;

    @Autowired
    private RestaurantRepository repository;

    @Autowired
    private UserRepository userRepository;

    private List<RestaurantEntity> savedRestaurants;
    private List<RestaurantEntity> addedRestaurants;
    private UUID foundID;
    private RestaurantEntity foundEntity;
    private RestaurantEntity restaurant1;
    private RestaurantEntity restaurant2;
    private RestaurantEntity restaurant;
    private boolean exists = false;

    @Before("@restaurant-service")
    public void setUp() {
        log.info("*********** Restaurant Service Test Context Set Up ***********");

        var owner = userRepository.save(buildDefaultOwner());
        restaurant = preBuildRestaurantEntity(owner);

        restaurant1 = buildCustomRestaurantEntity(NAME_1, ADDRESS_1, RESTAURANT_MUNICIPALITY, RESTAURANT_CITY, owner);
        restaurant2 = buildCustomRestaurantEntity(NAME_2, ADDRESS_2, RESTAURANT_MUNICIPALITY, RESTAURANT_CITY, owner);
        addedRestaurants = new ArrayList<RestaurantEntity>();
        savedRestaurants = new ArrayList<RestaurantEntity>();
    }

    @Given("the restaurant already exists in the database")
    public void givenRestaurantAlreadyExists() {
        repository.save(restaurant);
    }

    @Given("the restaurant does not exist in the database")
    public void givenRestaurantDoesNotExist() {
        assertThat(repository.findRestaurantByName(restaurant.getName())).isEmpty();
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

    @Given("a restaurant with name {string} exists in the database")
    public void givenRestaurantWithNameExists(String name) {
        var entity = repository.save(restaurant);

        assertThat(entity).isNotNull();
    }

    @Given("more than one restaurant in municipality {string} exists in the database")
    public void givenRestaurantsWithCustomMunicipalityExist(String municipality) {
        addedRestaurants.add(repository.save(restaurant1));
        addedRestaurants.add(repository.save(restaurant2));

        assertThat(repository.count()).isGreaterThan(1);
        assertThat(addedRestaurants.get(0).getLocation().getMunicipality()).isEqualTo(municipality);
        assertThat(addedRestaurants.get(1).getLocation().getMunicipality()).isEqualTo(municipality);
    }

    @Given("more than one restaurant in city {string} exists in the database")
    public void givenRestaurantsWithCustomCityExist(String city) {
        addedRestaurants.add(repository.save(restaurant1));
        addedRestaurants.add(repository.save(restaurant2));

        assertThat(repository.count()).isGreaterThan(1);
        assertThat(addedRestaurants.get(0).getLocation().getCity()).isEqualTo(city);
        assertThat(addedRestaurants.get(1).getLocation().getCity()).isEqualTo(city);
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
        var savedEntityID = repository.findRestaurantIdByName(restaurant.getName());

        foundEntity = service.findRestaurantById(savedEntityID);
    }

    @When("I search for restaurant by name {string}")
    public void whenSearchForRestaurantWithName(String name) {
        foundEntity = service.findRestaurantByName(name);
    }

    @When("I search for restaurants in municipality {string}")
    public void whenSearchForRestaurantsInMunicipality(String municipality) {
        savedRestaurants = service.findAllRestaurantsInMunicipality(municipality);
    }

    @When("I search for restaurants in city {string}")
    public void whenSearchForRestaurantsInCity(String city) {
        savedRestaurants = service.findAllRestaurantsInCity(city);
    }

    @When("I delete restaurant with ID from database")
    public void whenDeleteRestaurantFromDatabase() {
        var savedEntityID = repository.findRestaurantIdByName(restaurant.getName());

        service.deleteRestaurantById(savedEntityID);
    }

    @When("I check if that restaurant exists in the database")
    public void whenCheckIfRestaurantExists() {
        var savedEntityID = repository.findRestaurantIdByName(restaurant.getName());

        exists = service.checkIfRestaurantExistsById(savedEntityID);
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

        assertThat(restaurant.getId()).isEqualTo(repository.findById(foundID).get().getId());
    }

    @Then("an exception is thrown when I search for ID by name {string}")
    public void thenFindRestaurantIdByNameReturnsNull(String name) {
        assertThrows(RestaurantNotFoundException.class, () -> {
            service.findRestaurantIdByName(name);
        });
    }

    @Then("the restaurant entity is returned")
    public void thenReturnsCorrectRestaurantEntity() {
        assertThat(foundEntity).isNotNull();

        assertThat(foundEntity).isEqualTo(restaurant);
    }

    @Then("the restaurant with name {string} is found")
    public void thenCorrectRestaurantIsFound(String name) {
        assertThat(foundEntity).isNotNull();

        assertThat(foundEntity.getName()).isEqualTo(name);
    }

    @Then("an exception is thrown when I delete by ID")
    public void thenThrowsExceptionWhenDeleteById() {
        assertThrows(RestaurantNotFoundException.class, () -> {
            service.deleteRestaurantById(UUID.randomUUID());
        });
    }

    @Then("the method should return true")
    public void thenReturnsTrue() {
        assertThat(exists).isTrue();
    }

}