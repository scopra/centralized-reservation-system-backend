package com.ontime.crrs.business.rules.controller;

import com.ontime.crrs.business.mapper.rule.RuleMapper;
import com.ontime.crrs.business.reservation.model.Reservation;
import com.ontime.crrs.business.rules.discount.DiscountService;
import com.ontime.crrs.business.rules.model.Rule;
import com.ontime.crrs.business.rules.model.RuleModelAssembler;
import com.ontime.crrs.business.rules.order.OrderDiscount;
import com.ontime.crrs.persistence.restaurant.service.RestaurantService;
import com.ontime.crrs.persistence.rule.service.RuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.IanaLinkRelations;
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
    private final RuleModelAssembler modelAssembler;
    private final DiscountService discountService;

    @PostMapping("/{restaurantName}")
    public ResponseEntity<?> addNewRule(@PathVariable String restaurantName, @RequestBody Rule rule) {
        var restaurant = restaurantService.findRestaurantByName(restaurantName);

        var ruleEntity = mapper.modelToEntity(rule);
        ruleEntity.setRestaurant(restaurant);

        var entityModel = modelAssembler.toModel(mapper.entityToModel(ruleService.updateRule(ruleEntity)));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/{restaurantName}")
    public ResponseEntity<?> getRulesForRestaurant(@PathVariable String restaurantName) {
        restaurantService.findRestaurantByName(restaurantName);

        var ruleList = mapper.entitiesToModels(ruleService.findRulesByRestaurant(restaurantName));

        return ResponseEntity.ok(ruleList);
    }

    @GetMapping("/id/{ruleId}")
    public ResponseEntity<?> getRuleById(@PathVariable UUID ruleId) {
        var rule = mapper.entityToModel(ruleService.findRuleById(ruleId));

        return ResponseEntity.ok(rule);
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

    //TESTING
    @GetMapping("/test/disocunt")
    public OrderDiscount getDiscount(@RequestBody Reservation reservation) {
        return discountService.getDiscount(reservation);
    }

}