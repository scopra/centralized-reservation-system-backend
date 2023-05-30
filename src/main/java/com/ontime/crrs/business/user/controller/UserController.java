package com.ontime.crrs.business.user.controller;

import com.ontime.crrs.business.mapper.user.UserMapper;
import com.ontime.crrs.business.user.model.User;
import com.ontime.crrs.business.user.model.UserModelAssembler;
import com.ontime.crrs.persistence.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final UserModelAssembler modelAssembler;

    @GetMapping("/{email}")
    public EntityModel<User> getUserByEmail(@PathVariable String email) {
        var userEntity = userService.loadUserByEmail(email);

        var user = userMapper.entityToModel(userEntity);

        return modelAssembler.toModel(user);
    }

    @GetMapping("/all")
    public CollectionModel<?> getAllUsers() {
        var users =
                userMapper.entitiesToModels(userService.getAllUsers()).stream()
                        .map(modelAssembler :: toModel)
                        .toList();

        return CollectionModel.of(users, linkTo(methodOn(UserController.class)
                .getAllUsers()).withSelfRel());
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteUserByEmail(@PathVariable String email) {
        userService.deleteUser(email);

        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/all")
    public ResponseEntity<?> deleteAllUsers() {
        userService.deleteAllUsers();

        return ResponseEntity
                .noContent()
                .build();
    }

}