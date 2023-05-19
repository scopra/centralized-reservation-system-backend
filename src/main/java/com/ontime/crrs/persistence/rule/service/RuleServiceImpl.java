package com.ontime.crrs.persistence.rule.service;

import com.ontime.crrs.persistence.rule.entity.RuleEntity;
import com.ontime.crrs.persistence.rule.repository.RuleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RuleServiceImpl implements RuleService {

    private final RuleRepository repository;

    public RuleEntity findRuleById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rule with ID " + id + " not found."));
    }

    public List<RuleEntity> findRulesByRestaurant(String restaurantName) {
        return repository.findRulesByRestaurant_Name(restaurantName);
    }

    public RuleEntity updateRule(RuleEntity rule) {
        return repository.save(rule);
    }

    public void deleteRuleById(UUID id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Rule with ID " + id + " cannot be deleted.");
        }

        repository.deleteById(id);
    }

}