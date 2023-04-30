package com.ontime.crrs.cucumberglue.steps;
import com.ontime.crrs.business.table.model.Table;
import com.ontime.crrs.persistence.table.entity.TableEntity;
import com.ontime.crrs.persistence.table.repository.TableRepository;
import com.ontime.crrs.persistence.table.service.TableService;
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


import static com.ontime.crrs.helper.TableTestHelper.*;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class TableServiceSteps {

    private final TableEntity table1 = buildCustomTableEntity(TABLE_OCCUPANCY_STATUS, TABLE_CAPACITY);

    @Autowired
    private TableService service;

    @Autowired
    private TableRepository repository;

    private List<TableEntity> savedTables;

    private List<TableEntity> addedTables;
    private UUID foundID;

    private TableEntity foundEntity;

    @Before("@table-service")
    public void setUp() {
        log.info("*********** Table Service Test Context Set Up ***********");

        addedTables = new ArrayList<TableEntity>();
        savedTables = new ArrayList<TableEntity>();
    }

    @After("@table-service")
    public void tearDown() {
        log.info("*********** Table Service Test Context Tear Down ***********");

        repository.deleteAll();
        foundID = null;
        foundEntity = null;
    }

    @Given("more than one table exists in the database")
    public void givenMoreThanOneRestaurantInDatabase() {
        addedTables.add(repository.save(table1));

        assertThat(repository.count()).isGreaterThan(1);
    }
    @Given("the database is empty")
    public void givenDatabaseEmpty() {
        assertThat(repository.count()).isEqualTo(0);
    }

    @When("I search for all tables")
    public void whenSearchForAllRestaurants() {
        savedTables = service.findAllTables();
    }

    @Then("the returned list of tables is empty")
    public void thenReturnedListIsEmpty() {
        assertThat(savedTables.size()).isEqualTo(0);
    }

    @Then("the list of all tables is returned")
    public void thenListOfAllRestaurantsIsReturned() {
        assertThat(savedTables).hasSize(addedTables.size());

        assertThat(savedTables).containsExactlyInAnyOrderElementsOf(addedTables);
    }


}
