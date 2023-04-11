package com.ontime.crrs.persistence.restaurant;

import com.ontime.crrs.persistence.location.repository.LocationRepository;
import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import com.ontime.crrs.persistence.restaurant.repository.RestaurantRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

import static com.ontime.crrs.helper.RestaurantTestHelper.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantRepositorySteps {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    LocationRepository locationRepository;

    private String restaurantName;
    private String restaurantAddress;

    private RestaurantEntity foundEntity;
    private UUID foundID;
    private String foundAddress;
    private List<RestaurantEntity> foundEntities;

    @AfterEach
    void tearDown() {
        if (foundEntity != null) {
            restaurantRepository.delete(foundEntity);
        }
        locationRepository.deleteAll();
    }

    @Given("there is a restaurant with name {string} in the database")
    public void givenRestaurantExists(String name) {
        restaurantRepository.save(buildDefaultEntityWithName(name));
        restaurantName = name;
    }

    @Given("there is no restaurant with name {string} in the database")
    public void givenRestaurantDoesNotExistWithName(String name) {
        assertNull(foundEntity);
    }

    @Given("there is a restaurant with the address {string} in the database")
    public void givenRestaurantWithAddressExists(String address) {
        var savedEntity = restaurantRepository.save(buildDefaultEntityWithAddress(address));
        restaurantAddress = savedEntity.getLocation().getAddress();
    }

    @Given("there is more than one restaurant with the municipality {string} in the database")
    public void givenMultipleRestaurantsWithSameMunicipality(String municipality) {
        restaurantRepository.save(buildCustomRestaurantEntity("Name 1", "Vrazova 1",
                municipality, "Sarajevo"));
        restaurantRepository.save(buildCustomRestaurantEntity("Name 2", "Mis Irbina 1",
                municipality, "Sarajevo"));

    }

    @When("I search for a restaurant by name using {string}")
    public void whenSearchRestaurantByName(String name) {
        foundEntity = restaurantRepository.findRestaurantByName(name).orElse(null);
    }

    @When("I search for a restaurant ID by name using {string}")
    public void whenSearchForRestaurantIdByName(String name) {
        foundID = restaurantRepository.findRestaurantIdByName(name);
        foundEntity = restaurantRepository.findRestaurantByName(name).orElse(null);
    }

    @When("I search for a restaurant by address {string}")
    public void whenSearchForRestaurantByAddress(String address) {
        foundEntity = restaurantRepository.findRestaurantByLocation_Address(address).orElse(null);
        if (foundEntity != null) {
            foundAddress = foundEntity.getLocation().getAddress();
        }
    }

    @When("I search for restaurants by municipality {string}")
    public void whenSearchForRestaurantsByMunicipality(String municipality) {
        foundEntities = restaurantRepository.findRestaurantsByLocation_Municipality(municipality);
    }

    @Then("the method should return the restaurant entity for the given name")
    public void thenShouldReturnRestaurantEntity() {
        assertNotNull(foundEntity);
        assertNotNull(foundEntity.getLocation());
        assertEquals(restaurantName, foundEntity.getName());
    }

    @Then("the method should return null")
    public void thenShouldReturnNull() {
        assertNull(foundEntity);
    }

    @Then("the method should return null ID")
    public void thenShouldReturnNullID() {
        assertNull(foundID);
    }

    @Then("the method should return the ID of the restaurant for the given name")
    public void thenShouldReturnCorrectIdForGivenName() {
        assertNotNull(foundID);
        assertNull(foundEntity);
        assertNotNull(foundEntity.getLocation());
        assertEquals(foundID, foundEntity.getId());
    }

    @Then("the restaurant with the correct address is returned")
    public void thenShouldReturnCorrectRestaurant() {
        assertNotNull(foundEntity);
        assertNotNull(foundEntity.getLocation().getAddress());

        assertEquals(foundAddress, foundEntity.getLocation().getAddress());
    }

    @Then("the method should return a list of {int} restaurants with correct municipality")
    public void thenShouldReturnListOfRestaurants(int expectedSize) {
        assertEquals(expectedSize, foundEntities.size());
    }

    @Then("the method should return an empty list")
    public void thenShouldReturnEmptyList() {
        assertThat(foundEntities).isEmpty();
    }

}