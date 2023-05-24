package com.ontime.crrs.cucumberglue.steps;

import com.ontime.crrs.business.rules.util.RuleType;
import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import com.ontime.crrs.persistence.restaurant.repository.RestaurantRepository;
import com.ontime.crrs.persistence.rule.entity.RuleEntity;
import com.ontime.crrs.persistence.rule.repository.RuleRepository;
import com.ontime.crrs.persistence.user.repository.UserRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static com.ontime.crrs.helper.RestaurantTestHelper.*;
import static com.ontime.crrs.helper.RuleTestHelper.prebuildRule;
import static org.assertj.core.api.Assertions.assertThat;

//TODO: FIX
@Slf4j
public class RuleRepositorySteps {

    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserRepository userRepository;

    private RuleEntity firstRule;
    private RuleEntity secondRule;
    private RuleEntity thirdRule;
    private List<RuleEntity> rules;
    private RestaurantEntity restaurant;

    @Before("@rule-repository")
    public void beforeCallingRRepository() {
        log.info("*********** Rule Repository Scenario Call ***********");

        rules = new ArrayList<>();

        var owner = userRepository.save(buildDefaultOwner());
        restaurant = restaurantRepository.save(buildDefaultEntityWithName(RESTAURANT_NAME, owner));
    }

    @Given("the restaurant has three rules defined")
    public void givenThreeRulesDefined() {
        firstRule = ruleRepository.save(prebuildRule(RuleType.HAPPY_HOURS, restaurant));
        secondRule = ruleRepository.save(prebuildRule(RuleType.QUIET_TIMES, restaurant));
        thirdRule = ruleRepository.save(prebuildRule(RuleType.GROUP_SPECIALS, restaurant));
    }

    @Given("the restaurant has no rules defined")
    public void givenNoRulesDefined() {

    }

    @When("I search for rules by restaurant")
    public void whenSearchForRulesByRestaurant() {
        rules = ruleRepository.findRulesByRestaurant_Name(restaurant.getName());
    }

    @Then("the method returns a list of three rules")
    public void thenMethodReturnsListOfThreeRules() {
        assertThat(rules).hasSize(3);
    }

    @Then("the method returns an empty list of rules")
    public void thenMethodReturnsEmptyListOfRules() {
        assertThat(rules).isEmpty();
    }

}