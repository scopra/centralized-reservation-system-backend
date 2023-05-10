package com.ontime.crrs.business.rule.controller;

import com.ontime.crrs.business.rule.model.Rule;
import com.ontime.crrs.persistence.rule.service.RuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rules")
public class RuleController {

    private final RuleService ruleService;

    @PostMapping("/{restaurantName}")
    public ResponseEntity<?> addNewRule(@PathVariable String restaurantName, @RequestBody Rule rule) {
        return null;
    }

    @GetMapping("/{restaurantName}")
    public CollectionModel<?> getRulesForRestaurant(@PathVariable String restaurantName) {
        return null;
    }

    @PutMapping("/{restaurantName}")
    public ResponseEntity<?> editRule(@PathVariable String restaurantName, @RequestBody Rule rule) {
        return null;
    }

    @DeleteMapping("/owner/{ruleId}")
    public ResponseEntity<?> deleteRule(@PathVariable UUID ruleId) {
        return ResponseEntity
                .noContent()
                .build();
    }
}