package com.spring.trackingsystem.controller;

import com.spring.trackingsystem.dto.AuthResponse;
import com.spring.trackingsystem.dto.LoginRequest;
import com.spring.trackingsystem.dto.RegisterRequest;
import com.spring.trackingsystem.entity.User;
import com.spring.trackingsystem.repository.UserRepository;
import com.spring.trackingsystem.service.AuthService;
import com.spring.trackingsystem.service.JwtService;
import com.spring.trackingsystem.service.TokenBlackListService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final TokenBlackListService tokenBlackListService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        AuthResponse register = authService.register(registerRequest);
        return ResponseEntity
                .ok(register);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                );

        authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        User user = userRepository.findByEmail(loginRequest.getEmail());
        String token = jwtService.generateToken(user);
        return ResponseEntity
                .ok(new AuthResponse(token));
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String token = jwtService.extractToken(request);
        if (token != null && !jwtService.isTokenExpired(token)) {
            long expiration = jwtService.extractExpiration(token).getTime() - System.currentTimeMillis();
            tokenBlackListService.blackListToken(token, expiration);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
