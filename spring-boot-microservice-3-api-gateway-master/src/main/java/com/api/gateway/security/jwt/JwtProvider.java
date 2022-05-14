package com.api.gateway.security.jwt;

import org.springframework.security.core.Authentication;

import com.api.gateway.security.UserPrincipal;

import javax.servlet.http.HttpServletRequest;

public interface JwtProvider
{
    String generateToken(UserPrincipal auth);

    Authentication getAuthentication(HttpServletRequest request);

    boolean isTokenValid(HttpServletRequest request);
}
