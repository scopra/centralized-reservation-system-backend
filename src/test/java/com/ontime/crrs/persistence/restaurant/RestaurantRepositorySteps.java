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
    private LocationRepository locationRepository;

    private RestaurantEntity foundEntity;
    private UUID foundID;
    private String foundAddress;
    private List<RestaurantEntity> foundEntities;

    @AfterEach
    void tearDown() {
        /*if (foundEntity != null) {
            restaurantRepository.delete(foundEntity);
        }*/
        restaurantRepository.deleteAll();
        locationRepository.deleteAll();
    }

    @Given("there is a restaurant with name {string} in the database")
    public void givenRestaurantExists(String name) {
        restaurantRepository.save(buildDefaultEntityWithName(name));

        //assertThat(restaurantRepository.findRestaurantByName(name).isPresent());
        assert restaurantRepository.findRestaurantByName(name).isPresent();
    }

    @Given("there is no restaurant with name {string} in the database")
    public void givenRestaurantDoesNotExistWithName(String name) {
        //assertThat(restaurantRepository.findRestaurantByName(name).isEmpty());

        assert restaurantRepository.findRestaurantByName(name).isEmpty();
    }

    @Given("there is a restaurant with the address {string} in the database")
    public void givenRestaurantWithAddressExists(String address) {
        restaurantRepository.save(buildDefaultEntityWithAddress(address));

        //assertThat(restaurantRepository.findRestaurantByLocation_Address(address).isPresent());
        assert restaurantRepository.findRestaurantByLocation_Address(address).isPresent();
    }

    @Given("there is more than one restaurant with the municipality {string} in the database")
    public void givenMultipleRestaurantsWithSameMunicipality(String municipality) {
        restaurantRepository.save(buildCustomRestaurantEntity("Name 1", "Vrazova 1",
                municipality, "Sarajevo"));
        restaurantRepository.save(buildCustomRestaurantEntity("Name 2", "Mis Irbina 1",
                municipality, "Sarajevo"));
    }

    @Given("there is more than one restaurant with the city {string} in the database")
    public void givenMultipleRestaurantsFromTheSameCity(String city) {
        restaurantRepository.save(buildCustomRestaurantEntity("Name 1", "Vrazova 1", "Centar",
                city));
        restaurantRepository.save(buildCustomRestaurantEntity("Name 2", "Malta 11", "Novo Sarajevo",
                city));
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

    @When("I search for restaurants by city {string}")
    public void whenSearchForRestaurantsByCity(String city) {
        foundEntities = restaurantRepository.findRestaurantsByLocation_City(city);
    }

    @Then("the method should return the restaurant entity for {string}")
    public void thenShouldReturnRestaurantEntity(String name) {
        assertNotNull(foundEntity);
        assertNotNull(foundEntity.getLocation());
        assertEquals(name, foundEntity.getName());
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
        assertNotNull(foundEntity);
        assertNotNull(foundEntity.getLocation());
        assertEquals(foundID, foundEntity.getId());
    }

    @Then("the restaurant with the correct address is returned")
    public void thenShouldReturnCorrectRestaurant() {
        assertNotNull(foundEntity);
        assertNotNull(foundEntity.getLocation().getAddress());

        assertEquals(foundAddress, foundEntity.getLocation().getAddress());
    }

    @Then("the method should return a list of {int} restaurants")
    public void thenShouldReturnListOfRestaurants(int expectedSize) {
        assertEquals(expectedSize, foundEntities.size());
    }

    @Then("the method should return an empty list")
    public void thenShouldReturnEmptyList() {
        assertThat(foundEntities).isEmpty();
    }

}