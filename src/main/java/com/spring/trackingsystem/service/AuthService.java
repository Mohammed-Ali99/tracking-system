package com.spring.trackingsystem.service;

import com.spring.trackingsystem.dto.AuthResponse;
import com.spring.trackingsystem.dto.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest registerRequest);
}
