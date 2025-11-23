package com.spring.trackingsystem.service;


public interface TokenBlackListService {
    void blackListToken(String token, long expiration);

    boolean isTokenBlackListed(String token);
}
