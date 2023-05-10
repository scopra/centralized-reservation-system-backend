package com.ontime.crrs.business.rule.controller;

import com.ontime.crrs.business.mapper.rule.RuleMapper;
import com.ontime.crrs.business.rule.model.Rule;
import com.ontime.crrs.persistence.restaurant.service.RestaurantService;
import com.ontime.crrs.persistence.rule.service.RuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rules")
public class RuleController {

    private final RuleService ruleService;
    private final RestaurantService restaurantService;
    private final RuleMapper mapper;

    @PostMapping("/{restaurantName}")
    public ResponseEntity<?> addNewRule(@PathVariable String restaurantName, @RequestBody Rule rule) {
        var restaurant = restaurantService.findRestaurantByName(restaurantName);

        var ruleEntity = mapper.modelToEntity(rule);
        ruleEntity.setRestaurant(restaurant);

        var savedRule = mapper.entityToModel(ruleService.updateRule(ruleEntity));

        return ResponseEntity.ok(savedRule);
    }

    @GetMapping("/{restaurantName}")
    public ResponseEntity<?> getRulesForRestaurant(@PathVariable String restaurantName) {
        var restaurant = restaurantService.findRestaurantByName(restaurantName);

        var ruleList = mapper.entitiesToModels(ruleService.findRulesByRestaurant(restaurantName));

        return ResponseEntity.ok(ruleList);
    }

    @PutMapping("/{restaurantName}")
    public ResponseEntity<?> editRule(@PathVariable String restaurantName, @RequestBody Rule rule) {
        return null;
    }

    @DeleteMapping("/owner/{ruleId}")
    public ResponseEntity<?> deleteRule(@PathVariable UUID ruleId) {
        ruleService.deleteRuleById(ruleId);

        return ResponseEntity
                .noContent()
                .build();
    }

}