package com.spring.trackingsystem.service;

import com.spring.trackingsystem.entity.User;

public interface UserService {
    User findByEmail(String email);

    User findById(Long id);
}
