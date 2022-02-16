package com.itzikbarabie.moni.Services;

import com.itzikbarabie.moni.Entity.JwtRequest;
import com.itzikbarabie.moni.Exceptions.UnauthorizedException;

public interface AuthService {
    boolean authenticate(JwtRequest jwtRequest) throws UnauthorizedException;
}

