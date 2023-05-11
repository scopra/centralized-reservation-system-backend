package com.ontime.crrs.cucumberglue.steps;
import com.ontime.crrs.persistence.table.entity.TableEntity;
import com.ontime.crrs.persistence.table.repository.TableRepository;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

import static com.ontime.crrs.helper.RestaurantTestHelper.*;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class TableRepositorySteps {
    @Autowired
    private TableRepository tableRepository;

    private TableEntity foundEntity;

    private UUID foundID;

    private String foundAddress;

    private List<TableEntity> foundEntities;

    @Before("@table-repository")
    public void beforeCallingRRepository() {
        log.info("*********** Table Repository Scenario Call ***********");
    }
    @After("@table-repository")
    public void tearDown() {
        log.info("*********** Test Context Tear Down ***********");

        tableRepository.deleteAll();

        foundEntity = null;
        foundEntities = null;
        foundAddress = null;
        foundID = null;
    }

}
