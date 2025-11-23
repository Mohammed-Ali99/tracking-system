package com.spring.trackingsystem.service.impl;

import com.spring.trackingsystem.controller.exception.BusinessException;
import com.spring.trackingsystem.entity.User;
import com.spring.trackingsystem.repository.UserRepository;
import com.spring.trackingsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> BusinessException.buildNotFoundException(User.class, id)
        );
    }
}
