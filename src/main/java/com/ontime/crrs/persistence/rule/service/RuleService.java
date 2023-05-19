package com.ontime.crrs.persistence.rule.service;

import com.ontime.crrs.persistence.rule.entity.RuleEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface RuleService {

    RuleEntity findRuleById(UUID id);

    List<RuleEntity> findRulesByRestaurant(String restaurantName);

    RuleEntity updateRule(RuleEntity rule);

    void deleteRuleById(UUID id);

}