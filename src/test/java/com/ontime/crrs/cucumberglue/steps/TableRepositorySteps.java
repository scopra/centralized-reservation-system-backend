package com.ontime.crrs.cucumberglue.steps;

import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import com.ontime.crrs.persistence.restaurant.repository.RestaurantRepository;
import com.ontime.crrs.persistence.table.entity.TableEntity;
import com.ontime.crrs.persistence.table.repository.TableRepository;
import com.ontime.crrs.persistence.user.entity.UserEntity;
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
import static com.ontime.crrs.helper.TableTestHelper.buildDefaultEntityWithCapacity;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class TableRepositorySteps {

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserRepository userRepository;

    private List<TableEntity> foundEntities;
    private List<UUID> foundTables;
    private RestaurantEntity restaurant;
    private UserEntity owner;

    @Before("@table-repository")
    public void beforeCallingRRepository() {
        log.info("*********** Table Repository Scenario Call ***********");

        foundTables = new ArrayList<>();
        foundEntities = new ArrayList<>();

        owner = userRepository.save(buildDefaultOwner());
        restaurant = restaurantRepository.save(buildDefaultEntityWithName(RESTAURANT_NAME, owner));
    }

    @Given("there are no tables in restaurant")
    public void givenNoTablesInRestaurant() {

    }

    @Given("there are {int} tables in restaurant")
    public void givenThreeTablesInRestaurant(int amount) {
        tableRepository.save(buildDefaultEntityWithCapacity(5, restaurant));
        tableRepository.save(buildDefaultEntityWithCapacity(4, restaurant));
        tableRepository.save(buildDefaultEntityWithCapacity(3, restaurant));

        assertThat(tableRepository.count()).isEqualTo(amount);
    }

    @Given("there are no tables with capacity {int} for restaurant")
    public void givenNoTablesInRestaurantWithCapacity(int capacity) {

    }

    @Given("there are {int} tables with capacity {int} for restaurant")
    public void givenThreeTablesInRestaurantWithCapacity(int amount, int capacity) {
        tableRepository.save(buildDefaultEntityWithCapacity(capacity, restaurant));
        tableRepository.save(buildDefaultEntityWithCapacity(capacity, restaurant));
        tableRepository.save(buildDefaultEntityWithCapacity(capacity, restaurant));

        assertThat(tableRepository.count()).isEqualTo(amount);
    }

    @When("I search for a tables by restaurant")
    public void whenSearchForTablesByRestaurant() {
        foundEntities = tableRepository.findTablesByRestaurant_Name(restaurant.getName());
    }

    @When("I search for a tables by capacity {int} and restaurant")
    public void whenSearchForTablesByCapacityAndRestaurant(int capacity) {
        foundTables = tableRepository.findTableIdsByCapacityAndRestaurant(capacity, restaurant.getName());
    }

    @Then("the method should return an empty list of tables")
    public void thenMethodReturnsEmptyListOfTables() {
        assertThat(foundEntities).isEmpty();
    }

    @Then("the method should return list of {int} tables")
    public void thenMethodReturnsListOf3Tables(int size) {
        assertThat(foundEntities).hasSize(size);
    }

    @Then("the method should return list of {int} tables for capacity")
    public void thenMethodReturnsListOf3TablesForCapacity(int size) {
        assertThat(foundTables).hasSize(size);
    }

    @Then("the method should return an empty list of tables for capacity")
    public void thenMethodReturnsEmptyListOfTablesForCapacity() {
        assertThat(foundTables).isEmpty();
    }

}