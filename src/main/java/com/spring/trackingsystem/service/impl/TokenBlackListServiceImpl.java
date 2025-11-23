package com.spring.trackingsystem.service.impl;

import com.spring.trackingsystem.service.TokenBlackListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TokenBlackListServiceImpl implements TokenBlackListService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final String TOKEN_PREFIX = "tokenBlackList:";

    @Override
    public void blackListToken(String token, long expiration) {
        redisTemplate.opsForValue().set(TOKEN_PREFIX + token, true, expiration, TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean isTokenBlackListed(String token) {
        return redisTemplate.hasKey(TOKEN_PREFIX + token);
    }
}
