package com.ontime.crrs.rules.model;

import com.ontime.crrs.rules.controller.RuleController;
import io.micrometer.common.lang.NonNullApi;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@NonNullApi
@RequiredArgsConstructor
public class RuleModelAssembler implements RepresentationModelAssembler<Rule, EntityModel<Rule>> {

    @Override
    public EntityModel<Rule> toModel(Rule rule) {

        return EntityModel.of(rule,
                linkTo(methodOn(RuleController.class).getRuleById(rule.getId())).withSelfRel(),
                linkTo(methodOn(RuleController.class)
                        .getRulesForRestaurant(rule.getRestaurant().getName())).withRel("rules"));
    }

}