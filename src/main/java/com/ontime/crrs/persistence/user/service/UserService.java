package com.ontime.crrs.persistence.user.service;

import com.ontime.crrs.persistence.user.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserEntity registerUser(UserEntity user);

    UserEntity loadUserByEmail(String email);

    void deleteUser(String email);

}