package com.ontime.crrs.persistence.user.service;

import com.ontime.crrs.persistence.user.entity.UserEntity;
import com.ontime.crrs.persistence.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserEntity registerUser(UserEntity user) {
        return repository.save(user);
    }

    public UserEntity loadUserByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email " + email + " not found."));
    }

    public List<UserEntity> getAllUsers() {
        return repository.findAll();
    }

    public void deleteUser(String username) {
        var userId = loadUserByEmail(username).getId();

        repository.deleteById(userId);
    }

    public void deleteAllUsers() {
        repository.deleteAll();
    }

}