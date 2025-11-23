package com.spring.trackingsystem.service.impl;

import com.spring.trackingsystem.controller.exception.BusinessException;
import com.spring.trackingsystem.dto.AuthResponse;
import com.spring.trackingsystem.dto.RegisterRequest;
import com.spring.trackingsystem.entity.User;
import com.spring.trackingsystem.repository.UserRepository;
import com.spring.trackingsystem.service.AuthService;
import com.spring.trackingsystem.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        boolean existsByEmail = userRepository.existsByEmail(registerRequest.getEmail());
        if (existsByEmail) {
            throw BusinessException.buildBadRequestException("Email already exists");
        }

        User user = User.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(registerRequest.getRole())
                .build();

        userRepository.save(user);
        String token = jwtService.generateToken(user);

        return new AuthResponse(token);
    }
}
