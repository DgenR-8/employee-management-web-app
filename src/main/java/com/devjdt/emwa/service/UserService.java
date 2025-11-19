package com.devjdt.emwa.service;

import com.devjdt.emwa.entity.User;

public interface UserService {
    User registerUser(User user);
    boolean usernameExists(String username);
    boolean emailExists(String email);
}