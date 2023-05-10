package com.ontime.crrs.persistence.rule.repository;

import com.ontime.crrs.persistence.rule.entity.RuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RuleRepository extends JpaRepository<RuleEntity, UUID> {

    List<RuleEntity> findRulesByRestaurant_Name(String restaurantName);

}