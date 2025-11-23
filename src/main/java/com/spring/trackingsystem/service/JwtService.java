package com.spring.trackingsystem.service;

import com.spring.trackingsystem.entity.User;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;

public interface JwtService {

    String generateToken(User user);

    String extractEmail(String token);

    Date extractExpiration(String token);

    Boolean isTokenExpired(String token);

    String extractToken(HttpServletRequest request);
}
