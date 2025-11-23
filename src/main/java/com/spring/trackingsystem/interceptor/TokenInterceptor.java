package com.spring.trackingsystem.interceptor;

import com.spring.trackingsystem.controller.exception.BusinessException;
import com.spring.trackingsystem.service.JwtService;
import com.spring.trackingsystem.service.TokenBlackListService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

@Service
@RequiredArgsConstructor
public class TokenInterceptor implements HandlerInterceptor {

    private final TokenBlackListService tokenBlackListService;
    private final JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String token = jwtService.extractToken(request);
        if (token != null && tokenBlackListService.isTokenBlackListed(token)) {
            throw BusinessException.buildUnAuthorizedException("Token is blacklisted, please login again");
        }
        return true;
    }
}
