package com.ontime.crrs.persistence.restaurant.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class RestaurantRepositoryIT {

    @Autowired
    private RestaurantRepository repository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void whenRestaurantInDatabase_thenFindRestaurantIdByNameReturnsCorrectId() {
        //given
        //imam neki default object da spasim u bazu, savedEntity
        //uzmem mu ime entityName

        //when
        //id = findRestaurantIdByName(entityName)

        //then
        //assertEquals(id, savedEntity.getId())
    }

    @Test
    void whenRestaurantNotInDatabase_thenFindRestaurantIdByNameReturnsEmpty() {

    }
    /*
        findRestaurantByName(String name)

        Scenario: Restaurant exists in the database

        Test case: Search for a restaurant that exists in the database
        Expected result: The method should return the restaurant entity for the given name

        Scenario: Restaurant does not exist in the database

        Test case: Search for a restaurant that does not exist in the database
        Expected result: The method should return null or an empty optional

        findRestaurantIdByName(String name)
        Scenario: Restaurant exists in the database

        Test case: Search for a restaurant that exists in the database
        Expected result: The method should return the ID of the restaurant for the given name

        Scenario: Restaurant does not exist in the database
        Test case: Search for a restaurant that does not exist in the database
        Expected result: The method should return null or a default value such as -1 or 0
     */

    @Test
    void whenRestaurantInDatabase_thenFindRestaurantByNameReturnsRestaurant() {
    }

    @Test
    void whenRestaurantNotInDatabase_thenFindRestaurantByNameReturnsEmpty() {
    }

}