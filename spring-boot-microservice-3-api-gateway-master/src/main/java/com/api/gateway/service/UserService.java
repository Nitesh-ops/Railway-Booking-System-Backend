package com.api.gateway.service;

import java.util.Optional;

import com.api.gateway.model.Role;
import com.api.gateway.model.User;

public interface UserService
{
    User saveUser(User user);

    Optional<User> findByUsername(String username);

    void changeRole(Role newRole, String username);
}
