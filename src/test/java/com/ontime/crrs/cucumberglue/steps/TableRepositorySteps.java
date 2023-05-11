package com.ontime.crrs.cucumberglue.steps;

import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import com.ontime.crrs.persistence.restaurant.repository.RestaurantRepository;
import com.ontime.crrs.persistence.table.entity.TableEntity;
import com.ontime.crrs.persistence.table.repository.TableRepository;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

import static com.ontime.crrs.helper.RestaurantTestHelper.RESTAURANT_1;

@Slf4j
public class TableRepositorySteps {

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    private TableEntity foundEntity;
    private UUID foundID;
    private String foundAddress;
    private List<TableEntity> foundEntities;
    private RestaurantEntity restaurant;

    @Before("@table-repository")
    public void beforeCallingRRepository() {
        log.info("*********** Table Repository Scenario Call ***********");

        restaurant = restaurantRepository.save(RESTAURANT_1);
    }

    @After("@table-repository")
    public void tearDown() {
        log.info("*********** Test Context Tear Down ***********");

        restaurantRepository.deleteAll();


    }

    @Given("there are no tables in restaurant")
    public void givenNoTablesInRestaurant() {

    }

    @Given("there are 3 tables in restaurant")
    public void givenThreeTablesInRestaurant() {
        tableRepository.save()

    }

    @Given("there are no tables with given capacity for restaurant")
    public void givenNoTablesInRestaurantWithCapacity() {

    }

    @Given("there are 3 tables with given capacity for restaurant")
    public void givenThreeTablesInRestaurantWithCapacity() {

    }


}