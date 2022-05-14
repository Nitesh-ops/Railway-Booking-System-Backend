package com.api.gateway.service;

import com.api.gateway.model.User;

public interface AuthenticationService
{
    User signInAndReturnJWT(User signInRequest);
}
