package com.ontime.crrs.persistence.user.service;

import com.ontime.crrs.persistence.user.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserEntity registerUser(UserEntity user);

    UserEntity loadUserByEmail(String email);

    List<UserEntity> getAllUsers();

    void deleteUser(String email);

    void deleteAllUsers();

}